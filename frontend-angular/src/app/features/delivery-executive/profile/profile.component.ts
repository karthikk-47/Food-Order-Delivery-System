import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeliveryExecutiveService, ExecutiveProfile } from '../services/delivery-executive.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  template: `
    <app-navbar></app-navbar>
    <div class="profile-container">
      <h2>My Profile</h2>
      <div class="profile-card" *ngIf="profile">
        <div class="profile-header">
          <div class="avatar">{{ profile.name?.charAt(0) || 'D' }}</div>
          <div class="profile-info">
            <h3>{{ profile.name }}</h3>
            <p>📱 {{ profile.mobile }}</p>
            <div class="rating">⭐ {{ (profile.averageRating || 0).toFixed(1) }}</div>
          </div>
          <button class="edit-btn" (click)="toggleEdit()" *ngIf="!editing">✏️ Edit</button>
        </div>

        <div class="profile-stats">
          <div class="stat">
            <span class="value">{{ profile.totalDeliveries || 0 }}</span>
            <span class="label">Total Deliveries</span>
          </div>
          <div class="stat">
            <span class="value">₹{{ (profile.totalEarnings || 0).toFixed(0) }}</span>
            <span class="label">Total Earnings</span>
          </div>
        </div>

        <!-- View Mode -->
        <div class="profile-details" *ngIf="!editing">
          <div class="detail-row">
            <span class="label">Email:</span>
            <span class="value">{{ profile.email || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">License No:</span>
            <span class="value">{{ profile.licenseNo || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Aadhar No:</span>
            <span class="value">{{ profile.aadharNo || 'Not provided' }}</span>
          </div>
          <div class="detail-row">
            <span class="label">Vehicle:</span>
            <span class="value">{{ profile.vehicleType || 'N/A' }} - {{ profile.vehicleNumber || 'N/A' }}</span>
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
            <label>Vehicle Type</label>
            <select [(ngModel)]="editForm.vehicleType">
              <option value="">Select Vehicle</option>
              <option value="BICYCLE">Bicycle</option>
              <option value="MOTORCYCLE">Motorcycle</option>
              <option value="SCOOTER">Scooter</option>
              <option value="CAR">Car</option>
            </select>
          </div>
          <div class="form-group">
            <label>Vehicle Number</label>
            <input type="text" [(ngModel)]="editForm.vehicleNumber" placeholder="Enter vehicle number">
          </div>
          <div class="edit-actions">
            <button class="btn-cancel" (click)="cancelEdit()">Cancel</button>
            <button class="btn-save" (click)="saveProfile()">Save Changes</button>
          </div>
        </div>

        <div class="message success" *ngIf="successMessage">{{ successMessage }}</div>
        <div class="message error" *ngIf="errorMessage">{{ errorMessage }}</div>

        <div class="quick-actions" *ngIf="!editing">
          <a routerLink="/delivery-executive/orders" class="action-btn">
            <span class="icon">📦</span>
            <span>My Deliveries</span>
          </a>
          <a routerLink="/delivery-executive/wallet" class="action-btn">
            <span class="icon">💰</span>
            <span>Wallet</span>
          </a>
          <button class="action-btn logout" (click)="logout()">
            <span class="icon">🚪</span>
            <span>Logout</span>
          </button>
        </div>
      </div>
      <div class="loading" *ngIf="loading">Loading profile...</div>
    </div>
  `,
  styles: [`
    .profile-container { padding: 24px; max-width: 800px; margin: 0 auto; }
    h2 { color: #333; margin-bottom: 24px; }
    .profile-card { background: white; border-radius: 12px; padding: 32px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .profile-header { display: flex; gap: 20px; margin-bottom: 32px; padding-bottom: 24px; border-bottom: 1px solid #f0f0f0; align-items: flex-start; }
    .avatar { width: 80px; height: 80px; border-radius: 50%; background: linear-gradient(135deg, #00c853 0%, #009624 100%); display: flex; align-items: center; justify-content: center; color: white; font-size: 32px; font-weight: 700; flex-shrink: 0; }
    .profile-info { flex: 1; }
    .profile-info h3 { margin: 0 0 8px 0; font-size: 24px; }
    .profile-info p { margin: 0 0 8px 0; color: #666; }
    .rating { display: inline-block; padding: 4px 12px; background: #fff3e0; color: #e65100; border-radius: 12px; font-weight: 600; }
    .edit-btn { padding: 8px 16px; background: #e3f2fd; color: #1976d2; border: none; border-radius: 6px; cursor: pointer; font-weight: 500; }
    .profile-stats { display: grid; grid-template-columns: repeat(2, 1fr); gap: 24px; margin-bottom: 32px; }
    .stat { text-align: center; padding: 20px; background: #f5f7fa; border-radius: 8px; }
    .stat .value { display: block; font-size: 28px; font-weight: 700; color: #333; margin-bottom: 8px; }
    .stat .label { color: #666; font-size: 14px; }
    .profile-details { margin-bottom: 24px; }
    .detail-row { display: flex; justify-content: space-between; padding: 16px 0; border-bottom: 1px solid #f0f0f0; }
    .detail-row:last-child { border-bottom: none; }
    .detail-row .label { color: #666; font-weight: 600; }
    .detail-row .value { color: #333; }
    .profile-edit { margin-bottom: 24px; }
    .profile-edit h4 { margin: 0 0 16px 0; color: #333; }
    .form-group { margin-bottom: 16px; }
    .form-group label { display: block; margin-bottom: 6px; font-weight: 600; color: #555; }
    .form-group input, .form-group select { width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; }
    .form-group input:focus, .form-group select:focus { outline: none; border-color: #00c853; }
    .edit-actions { display: flex; gap: 12px; margin-top: 20px; }
    .btn-cancel { padding: 10px 20px; background: #f5f5f5; border: 1px solid #ddd; border-radius: 6px; cursor: pointer; }
    .btn-save { padding: 10px 20px; background: #00c853; color: white; border: none; border-radius: 6px; cursor: pointer; }
    .btn-save:hover { background: #009624; }
    .message { padding: 12px; border-radius: 6px; margin: 16px 0; }
    .message.success { background: #e8f5e9; color: #2e7d32; }
    .message.error { background: #ffebee; color: #c62828; }
    .quick-actions { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; margin-top: 24px; }
    .action-btn { display: flex; flex-direction: column; align-items: center; gap: 8px; padding: 16px; background: #f5f7fa; border-radius: 8px; text-decoration: none; color: #333; border: none; cursor: pointer; transition: all 0.2s; }
    .action-btn:hover { background: #e8eaed; transform: translateY(-2px); }
    .action-btn .icon { font-size: 24px; }
    .action-btn.logout { background: #ffebee; color: #c62828; }
    .action-btn.logout:hover { background: #ffcdd2; }
    .loading { text-align: center; padding: 48px; color: #666; }
  `]
})
export class ProfileComponent implements OnInit {
  profile: ExecutiveProfile | null = null;
  loading = false;
  editing = false;
  executiveId: number = 0;
  editForm = { name: '', email: '', vehicleType: '', vehicleNumber: '' };
  successMessage = '';
  errorMessage = '';

  constructor(
    private deliveryService: DeliveryExecutiveService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) this.executiveId = user.id;
  }

  ngOnInit() {
    this.loadProfile();
  }

  loadProfile() {
    this.loading = true;
    this.deliveryService.getProfile(this.executiveId).subscribe({
      next: (profile) => {
        this.profile = profile;
        this.editForm = {
          name: profile.name || '',
          email: profile.email || '',
          vehicleType: profile.vehicleType || '',
          vehicleNumber: profile.vehicleNumber || ''
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
        vehicleType: this.profile.vehicleType || '',
        vehicleNumber: this.profile.vehicleNumber || ''
      };
    }
  }

  saveProfile() {
    this.deliveryService.updateProfile(this.executiveId, this.editForm).subscribe({
      next: () => {
        if (this.profile) {
          this.profile = { ...this.profile, ...this.editForm };
        }
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

  logout() {
    this.authService.logout();
  }
}
