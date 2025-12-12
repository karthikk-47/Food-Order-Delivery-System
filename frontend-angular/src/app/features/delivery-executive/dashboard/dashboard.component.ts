import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeliveryExecutiveService, OrderSummary } from '../services/delivery-executive.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {
  orders: OrderSummary[] = [];
  loading = false;
  error = '';
  isOnline = false;
  sortBy = 'optimal';
  currentLocation = { latitude: 0, longitude: 0 };
  executiveId: number = 0;
  locationSubscription?: Subscription;

  sortOptions = [
    { value: 'optimal', label: 'Optimal (Recommended)' },
    { value: 'commission', label: 'Highest Commission' },
    { value: 'distance', label: 'Nearest First' },
    { value: 'priority', label: 'High Priority' }
  ];

  constructor(
    private deliveryService: DeliveryExecutiveService
  ) {
    // Executive id will be set from auth storage or another mechanism; ensure it is set before API calls.
  }

  ngOnInit() {
    this.getCurrentLocation();
    this.startLocationTracking();
  }

  ngOnDestroy() {
    if (this.locationSubscription) {
      this.locationSubscription.unsubscribe();
    }
  }

  getCurrentLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          this.currentLocation = {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude
          };
          if (this.isOnline) {
            this.loadNearbyOrders();
          }
        },
        (error) => {
          console.error('Error getting location:', error);
          this.error = 'Unable to get your location. Please enable location services.';
        }
      );
    }
  }

  startLocationTracking() {
    // Update location every 30 seconds when online
    this.locationSubscription = interval(30000).subscribe(() => {
      if (this.isOnline) {
        this.getCurrentLocation();
        this.updateLocationOnServer();
      }
    });
  }

  updateLocationOnServer() {
    if (this.currentLocation.latitude && this.currentLocation.longitude) {
      this.deliveryService.updateLocation(
        this.executiveId,
        this.currentLocation.latitude,
        this.currentLocation.longitude
      ).subscribe();
    }
  }

  toggleOnlineStatus() {
    this.isOnline = !this.isOnline;
    this.deliveryService.updateStatus(this.executiveId, this.isOnline).subscribe({
      next: () => {
        if (this.isOnline) {
          this.loadNearbyOrders();
        } else {
          this.orders = [];
        }
      },
      error: (err) => {
        this.error = 'Failed to update status';
        this.isOnline = !this.isOnline; // Revert
      }
    });
  }

  loadNearbyOrders() {
    if (!this.currentLocation.latitude || !this.currentLocation.longitude) {
      this.getCurrentLocation();
      return;
    }

    this.loading = true;
    this.error = '';

    this.deliveryService.getNearbyOrders(
      this.executiveId,
      this.currentLocation.latitude,
      this.currentLocation.longitude,
      this.sortBy
    ).subscribe({
      next: (orders) => {
        this.orders = orders;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load orders';
        this.loading = false;
      }
    });
  }

  onSortChange() {
    if (this.isOnline) {
      this.loadNearbyOrders();
    }
  }

  acceptOrder(orderId: number) {
    this.deliveryService.acceptOrder(this.executiveId, orderId).subscribe({
      next: () => {
        this.loadNearbyOrders();
      },
      error: (err) => {
        this.error = 'Failed to accept order';
      }
    });
  }

  getDistanceInKm(meters: number): string {
    return (meters / 1000).toFixed(1);
  }

  getPriorityClass(priority: string): string {
    switch (priority) {
      case 'HIGH': return 'priority-high';
      case 'MEDIUM': return 'priority-medium';
      case 'LOW': return 'priority-low';
      default: return '';
    }
  }
}
