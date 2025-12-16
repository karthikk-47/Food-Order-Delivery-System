import { Component, EventEmitter, Input, Output, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as L from 'leaflet';

// Fix for default marker icons in Leaflet
delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon-2x.png',
  iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon.png',
  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png',
});

export interface LocationData {
  lat: number;
  lng: number;
  address: string;
}

@Component({
  selector: 'app-map-picker',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="map-picker-container">
      <div class="map-header">
        <h4>📍 Select Location on Map</h4>
        <button class="btn-close" (click)="close.emit()">✕</button>
      </div>
      
      <div class="search-box">
        <input 
          type="text" 
          [(ngModel)]="searchQuery" 
          placeholder="Search for a place..."
          (keyup.enter)="searchLocation()"
        />
        <button (click)="searchLocation()" [disabled]="searching">
          {{ searching ? '...' : '🔍' }}
        </button>
        <button (click)="getCurrentLocation()" [disabled]="locating" title="Use current location">
          {{ locating ? '...' : '📍' }}
        </button>
      </div>

      <div id="map-{{mapId}}" class="map-container"></div>
      
      <div class="selected-address" *ngIf="selectedAddress">
        <strong>Selected:</strong> {{ selectedAddress }}
      </div>

      <div class="map-actions">
        <button class="btn-cancel" (click)="close.emit()">Cancel</button>
        <button class="btn-confirm" (click)="confirmLocation()" [disabled]="!selectedLat">
          Confirm Location
        </button>
      </div>
    </div>
  `,
  styles: [`
    .map-picker-container {
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: white;
      border-radius: 12px;
      box-shadow: 0 4px 20px rgba(0,0,0,0.3);
      z-index: 1000;
      width: 90%;
      max-width: 600px;
      max-height: 90vh;
      overflow: hidden;
    }
    .map-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 16px;
      border-bottom: 1px solid #eee;
    }
    .map-header h4 { margin: 0; }
    .btn-close {
      background: none;
      border: none;
      font-size: 20px;
      cursor: pointer;
      padding: 4px 8px;
    }
    .search-box {
      display: flex;
      gap: 8px;
      padding: 12px 16px;
      background: #f5f5f5;
    }
    .search-box input {
      flex: 1;
      padding: 10px 12px;
      border: 1px solid #ddd;
      border-radius: 6px;
      font-size: 14px;
    }
    .search-box button {
      padding: 10px 16px;
      border: none;
      background: #1976d2;
      color: white;
      border-radius: 6px;
      cursor: pointer;
    }
    .search-box button:disabled { opacity: 0.6; }
    .map-container {
      height: 350px;
      width: 100%;
    }
    .selected-address {
      padding: 12px 16px;
      background: #e3f2fd;
      font-size: 13px;
      border-top: 1px solid #ddd;
    }
    .map-actions {
      display: flex;
      gap: 12px;
      padding: 16px;
      justify-content: flex-end;
      border-top: 1px solid #eee;
    }
    .btn-cancel {
      padding: 10px 20px;
      background: #f5f5f5;
      border: 1px solid #ddd;
      border-radius: 6px;
      cursor: pointer;
    }
    .btn-confirm {
      padding: 10px 20px;
      background: #4caf50;
      color: white;
      border: none;
      border-radius: 6px;
      cursor: pointer;
    }
    .btn-confirm:disabled { opacity: 0.5; cursor: not-allowed; }
  `]
})
export class MapPickerComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() initialLat: number = 20.5937; // Default: India center
  @Input() initialLng: number = 78.9629;
  @Input() initialAddress: string = '';
  @Output() locationSelected = new EventEmitter<LocationData>();
  @Output() close = new EventEmitter<void>();

  mapId = Math.random().toString(36).substr(2, 9);
  private map!: L.Map;
  private marker!: L.Marker;
  
  searchQuery = '';
  searching = false;
  locating = false;
  selectedLat: number | null = null;
  selectedLng: number | null = null;
  selectedAddress = '';

  ngOnInit() {
    this.selectedAddress = this.initialAddress;
  }

  ngAfterViewInit() {
    setTimeout(() => this.initMap(), 100);
  }

  ngOnDestroy() {
    if (this.map) {
      this.map.remove();
    }
  }

  private initMap() {
    this.map = L.map(`map-${this.mapId}`).setView([this.initialLat, this.initialLng], 13);
    
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    this.marker = L.marker([this.initialLat, this.initialLng], { draggable: true }).addTo(this.map);
    this.selectedLat = this.initialLat;
    this.selectedLng = this.initialLng;

    // Handle marker drag
    this.marker.on('dragend', () => {
      const pos = this.marker.getLatLng();
      this.selectedLat = pos.lat;
      this.selectedLng = pos.lng;
      this.reverseGeocode(pos.lat, pos.lng);
    });

    // Handle map click
    this.map.on('click', (e: L.LeafletMouseEvent) => {
      this.selectedLat = e.latlng.lat;
      this.selectedLng = e.latlng.lng;
      this.marker.setLatLng(e.latlng);
      this.reverseGeocode(e.latlng.lat, e.latlng.lng);
    });

    // Get initial address if coordinates provided
    if (this.initialLat && this.initialLng) {
      this.reverseGeocode(this.initialLat, this.initialLng);
    }
  }

  getCurrentLocation() {
    if (!navigator.geolocation) {
      alert('Geolocation not supported');
      return;
    }
    this.locating = true;
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        const lat = pos.coords.latitude;
        const lng = pos.coords.longitude;
        this.map.setView([lat, lng], 16);
        this.marker.setLatLng([lat, lng]);
        this.selectedLat = lat;
        this.selectedLng = lng;
        this.reverseGeocode(lat, lng);
        this.locating = false;
      },
      () => {
        alert('Could not get your location');
        this.locating = false;
      }
    );
  }

  searchLocation() {
    if (!this.searchQuery.trim()) return;
    this.searching = true;
    
    fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(this.searchQuery)}&limit=1`)
      .then(res => res.json())
      .then(data => {
        if (data && data.length > 0) {
          const lat = parseFloat(data[0].lat);
          const lng = parseFloat(data[0].lon);
          this.map.setView([lat, lng], 16);
          this.marker.setLatLng([lat, lng]);
          this.selectedLat = lat;
          this.selectedLng = lng;
          this.selectedAddress = data[0].display_name;
        } else {
          alert('Location not found');
        }
        this.searching = false;
      })
      .catch(() => {
        alert('Search failed');
        this.searching = false;
      });
  }

  private reverseGeocode(lat: number, lng: number) {
    fetch(`https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`)
      .then(res => res.json())
      .then(data => {
        if (data && data.display_name) {
          this.selectedAddress = data.display_name;
        }
      })
      .catch(() => {});
  }

  confirmLocation() {
    if (this.selectedLat && this.selectedLng) {
      this.locationSelected.emit({
        lat: this.selectedLat,
        lng: this.selectedLng,
        address: this.selectedAddress
      });
    }
  }
}
