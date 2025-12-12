import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DeliveryExecutiveService } from '../services/delivery-executive.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  template: `
    <app-navbar></app-navbar>
    <div class="orders-container">
      <h2>My Orders</h2>
      <div class="tabs">
        <button [class.active]="activeTab === 'active'" (click)="activeTab = 'active'; loadOrders()">
          Active Orders
        </button>
        <button [class.active]="activeTab === 'delivered'" (click)="activeTab = 'delivered'; loadOrders()">
          Delivered Orders
        </button>
      </div>
      
      <div class="orders-list" *ngIf="!loading">
        <div class="order-item" *ngFor="let order of orders">
          <h3>Order #{{ order.id }}</h3>
          <p>Status: {{ order.orderStatus }}</p>
          <p>Amount: ₹{{ order.amount }}</p>
        </div>
        <p *ngIf="orders.length === 0">No orders found</p>
      </div>
      
      <div *ngIf="loading">Loading...</div>
    </div>
  `,
  styles: [`
    .orders-container { padding: 24px; max-width: 1200px; margin: 0 auto; }
    .tabs { display: flex; gap: 16px; margin-bottom: 24px; }
    .tabs button { padding: 12px 24px; border: none; background: #f0f0f0; cursor: pointer; border-radius: 8px; }
    .tabs button.active { background: #667eea; color: white; }
    .order-item { background: white; padding: 20px; margin-bottom: 16px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
  `]
})
export class OrdersComponent implements OnInit {
  orders: any[] = [];
  loading = false;
  activeTab = 'active';
  executiveId: number = 0;

  constructor(
    private deliveryService: DeliveryExecutiveService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) this.executiveId = user.id;
  }

  ngOnInit() {
    this.loadOrders();
  }

  loadOrders() {
    this.loading = true;
    const service = this.activeTab === 'active' 
      ? this.deliveryService.getActiveOrders(this.executiveId)
      : this.deliveryService.getDeliveredOrders(this.executiveId);
    
    service.subscribe({
      next: (orders) => { this.orders = orders; this.loading = false; },
      error: () => this.loading = false
    });
  }
}
