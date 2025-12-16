import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HomemakerService } from '../services/homemaker.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { MapPickerComponent, LocationData } from '../../../shared/components/map-picker/map-picker.component';

@Component({
  selector: 'app-homemaker-profile',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent, MapPickerComponent],
  template: `
    <app-navbar></app-navbar>
    <div class="profile-container">
      <h2>My Profile</h2>
      <div class="profile-card" *ngIf="profile">
        <div class="profile-header">
          <div class="avatar">{{ profile.name?.charAt(0) || 'H' }}</div>
          <div class="profile-info">
            <h3>{{ profile.name }}</h3>
            <p>📱 {{ profile.mobile }}</p>
            <span class="role-badge">Home Chef</span>
          </div>
          <button class="edit-btn" (click)="toggleEdit()" *ngIf="!editing">✏️ Edit</button>
        </div>

        <div class="profile-stats">
          <div class="stat">
            <span class="value">{{ profile.totalOrders || 0 }}</span>
            <span class="label">Total Orders</span>
          </div>
          <div class="stat">
            <span class="value">₹{{ profile.totalEarnings || 0 }}</span>
            <span class="label">Total Earnings</span>
          </div>
          <div class="stat">
            <span class="value">⭐ {{ (profile.rating || 0).toFixed(1) }}</span>
            <span class="label">Rating</span>
          </div>
        </div>

        <!-- View Mode -->
        <div class="profile-details" *ngIf="!editing">
          <div class="detail-row">
            <span class="label">Email:</span>
            <span class="value">{{ profile.email || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Address:</span>
            <span class="value">{{ profile.address || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">FSSAI License:</span>
            <span class="value">{{ profile.fssaiLicense || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Status:</span>
            <span class="value status" [class]="profile.approvalStatus?.toLowerCase()">
              {{ profile.approvalStatus || 'PENDING' }}
            </span>
          </div>
        </div>

        <!-- Edit Mode -->
        <div class="profile-edit" *ngIf="editing">
          <h4>Edit Profile</h4>
          <div class="form-group">
            <label>Name</label>
            <input type="text" [(ngModel)]="editForm.name" placeholder="Enter your name">
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" [(ngModel)]="editForm.email" placeholder="Enter your email">
          </div>
          <div class="form-group">
            <label>Address</label>
            <textarea [(ngModel)]="editForm.address" placeholder="Enter your kitchen address" rows="3"></textarea>
          </div>
          <div class="form-group location-buttons">
            <button class="btn-location" type="button" (click)="fillAddressFromLocation()" [disabled]="locationLoading">
              {{ locationLoading ? '⏳ Detecting...' : '📍 Use Current Location' }}
            </button>
            <button class="btn-location btn-map" type="button" (click)="showMapPicker = true">
              🗺️ Pick on Map
            </button>
          </div>
          <div class="form-group">
            <label>FSSAI License (Optional)</label>
            <input type="text" [(ngModel)]="editForm.fssaiLicense" placeholder="Enter FSSAI license number">
          </div>
          <div class="edit-actions">
            <button class="btn-cancel" (click)="cancelEdit()">Cancel</button>
            <button class="btn-save" (click)="saveProfile()">Save Changes</button>
          </div>
        </div>

        <div class="message success" *ngIf="successMessage">{{ successMessage }}</div>
        <div class="message error" *ngIf="errorMessage">{{ errorMessage }}</div>

        <div class="quick-actions" *ngIf="!editing">
          <a routerLink="/homemaker/menu" class="action-btn">
            <span class="icon">🍽️</span>
            <span>Manage Menu</span>
          </a>
          <a routerLink="/homemaker/orders" class="action-btn">
            <span class="icon">📦</span>
            <span>Orders</span>
          </a>
          <button class="action-btn logout" (click)="logout()">
            <span class="icon">🚪</span>
            <span>Logout</span>
          </button>
        </div>
      </div>
      <div class="loading" *ngIf="loading">Loading profile...</div>
    </div>

    <!-- Map Picker Modal -->
    <div class="map-overlay" *ngIf="showMapPicker" (click)="showMapPicker = false"></div>
    <app-map-picker 
      *ngIf="showMapPicker"
      [initialLat]="selectedLat"
      [initialLng]="selectedLng"
      [initialAddress]="editForm.address"
      (locationSelected)="onLocationSelected($event)"
      (close)="showMapPicker = false">
    </app-map-picker>
  `,
  styles: [`
    .profile-container { padding: 24px; max-width: 800px; margin: 0 auto; }
    h2 { color: #333; margin-bottom: 24px; }
    .profile-card { background: white; border-radius: 12px; padding: 32px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .profile-header { display: flex; gap: 20px; margin-bottom: 32px; padding-bottom: 24px; border-bottom: 1px solid #f0f0f0; align-items: flex-start; }
    .avatar { width: 80px; height: 80px; border-radius: 50%; background: linear-gradient(135deg, #FF8A00 0%, #E67E00 100%); display: flex; align-items: center; justify-content: center; color: white; font-size: 32px; font-weight: 700; flex-shrink: 0; }
    .profile-info { flex: 1; }
    .profile-info h3 { margin: 0 0 8px 0; font-size: 24px; }
    .profile-info p { margin: 0 0 8px 0; color: #666; }
    .role-badge { display: inline-block; padding: 4px 12px; background: #fff3e0; color: #e65100; border-radius: 12px; font-size: 12px; font-weight: 600; }
    .edit-btn { padding: 8px 16px; background: #fff3e0; color: #e65100; border: none; border-radius: 6px; cursor: pointer; font-weight: 500; }
    .profile-stats { display: grid; grid-template-columns: repeat(3, 1fr); gap: 24px; margin-bottom: 32px; }
    .stat { text-align: center; padding: 20px; background: #f5f7fa; border-radius: 8px; }
    .stat .value { display: block; font-size: 24px; font-weight: 700; color: #333; margin-bottom: 8px; }
    .stat .label { color: #666; font-size: 14px; }
    .profile-details { margin-bottom: 24px; }
    .detail-row { display: flex; justify-content: space-between; padding: 16px 0; border-bottom: 1px solid #f0f0f0; }
    .detail-row:last-child { border-bottom: none; }
    .detail-row .label { color: #666; font-weight: 600; }
    .detail-row .value { color: #333; }
    .status.approved { color: #2e7d32; background: #e8f5e9; padding: 4px 12px; border-radius: 12px; }
    .status.pending { color: #f57c00; background: #fff3e0; padding: 4px 12px; border-radius: 12px; }
    .status.rejected { color: #c62828; background: #ffebee; padding: 4px 12px; border-radius: 12px; }
    .profile-edit { margin-bottom: 24px; }
    .profile-edit h4 { margin: 0 0 16px 0; color: #333; }
    .form-group { margin-bottom: 16px; }
    .form-group label { display: block; margin-bottom: 6px; font-weight: 600; color: #555; }
    .form-group input, .form-group textarea { width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; }
    .form-group input:focus, .form-group textarea:focus { outline: none; border-color: #FF8A00; }
    .edit-actions { display: flex; gap: 12px; margin-top: 20px; }
    .btn-cancel { padding: 10px 20px; background: #f5f5f5; border: 1px solid #ddd; border-radius: 6px; cursor: pointer; }
    .btn-save { padding: 10px 20px; background: #FF8A00; color: white; border: none; border-radius: 6px; cursor: pointer; }
    .btn-save:hover { background: #E67E00; }
    .message { padding: 12px; border-radius: 6px; margin: 16px 0; }
    .message.success { background: #e8f5e9; color: #2e7d32; }
    .message.error { background: #ffebee; color: #c62828; }
    .quick-actions { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; margin-top: 24px; }
    .action-btn { display: flex; flex-direction: column; align-items: center; gap: 8px; padding: 16px; background: #f5f7fa; border-radius: 8px; text-decoration: none; color: #333; border: none; cursor: pointer; transition: all 0.2s; }
    .action-btn:hover { background: #e8eaed; transform: translateY(-2px); }
    .action-btn .icon { font-size: 24px; }
    .action-btn.logout { background: #ffebee; color: #c62828; }
    .loading { text-align: center; padding: 48px; color: #666; }
    .location-buttons { display: flex; gap: 12px; flex-wrap: wrap; }
    .btn-location { padding: 10px 16px; border: 1px solid #ddd; border-radius: 6px; cursor: pointer; background: #f5f5f5; font-size: 14px; }
    .btn-location:hover { background: #e8e8e8; }
    .btn-location:disabled { opacity: 0.6; cursor: not-allowed; }
    .btn-map { background: #fff3e0; border-color: #FF8A00; color: #e65100; }
    .btn-map:hover { background: #ffe0b2; }
    .map-overlay { position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: rgba(0,0,0,0.5); z-index: 999; }
  `]
})
export class HomemakerProfileComponent implements OnInit {
  profile: any = null;
  loading = false;
  editing = false;
  homemakerId: number = 0;
  editForm = { name: '', email: '', address: '', fssaiLicense: '' };
  successMessage = '';
  errorMessage = '';
  locationLoading = false;
  showMapPicker = false;
  selectedLat = 20.5937; // Default: India center
  selectedLng = 78.9629;

  constructor(
    private homemakerService: HomemakerService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) this.homemakerId = user.id;
  }

  ngOnInit() {
    this.loadProfile();
  }

  loadProfile() {
    this.loading = true;
    this.homemakerService.getProfile(this.homemakerId).subscribe({
      next: (profile) => {
        this.profile = profile;
        this.editForm = {
          name: profile.name || '',
          email: profile.email || '',
          address: profile.address || '',
          fssaiLicense: profile.fssaiLicense || ''
        };
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  toggleEdit() {
    this.editing = true;
    this.successMessage = '';
    this.errorMessage = '';
  }

  cancelEdit() {
    this.editing = false;
    if (this.profile) {
      this.editForm = {
        name: this.profile.name || '',
        email: this.profile.email || '',
        address: this.profile.address || '',
        fssaiLicense: this.profile.fssaiLicense || ''
      };
    }
  }

  saveProfile() {
    this.homemakerService.updateProfile(this.homemakerId, this.editForm).subscribe({
      next: () => {
        this.profile = { ...this.profile, ...this.editForm };
        this.editing = false;
        this.successMessage = 'Profile updated successfully!';
        setTimeout(() => this.successMessage = '', 3000);
      },
      error: () => {
        this.errorMessage = 'Failed to update profile. Please try again.';
        setTimeout(() => this.errorMessage = '', 3000);
      }
    });
  }

  fillAddressFromLocation() {
    this.errorMessage = '';
    this.successMessage = '';

    if (!navigator.geolocation) {
      this.errorMessage = 'Geolocation is not supported by your browser.';
      return;
    }

    this.locationLoading = true;
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        this.selectedLat = lat;
        this.selectedLng = lng;
        this.homemakerService.reverseGeocode(lat, lng).subscribe({
          next: (response) => {
            const formatted = response?.display_name;
            if (formatted) {
              this.editForm.address = formatted;
              this.successMessage = 'Address fetched from your current location.';
            } else {
              this.errorMessage = 'Could not determine address for your location.';
            }
            this.locationLoading = false;
          },
          error: () => {
            this.errorMessage = 'Failed to fetch address from location.';
            this.locationLoading = false;
          }
        });
      },
      () => {
        this.errorMessage = 'Unable to get your location. Please check location permissions.';
        this.locationLoading = false;
      }
    );
  }

  logout() {
    this.authService.logout();
  }

  onLocationSelected(location: LocationData) {
    this.editForm.address = location.address;
    this.selectedLat = location.lat;
    this.selectedLng = location.lng;
    this.showMapPicker = false;
    this.successMessage = 'Location selected from map.';
    setTimeout(() => this.successMessage = '', 3000);
  }
}
