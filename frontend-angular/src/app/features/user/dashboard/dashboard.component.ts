import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService, Order, UserProfile } from '../../../core/services/user.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  profile: UserProfile | null = null;
  recentOrders: Order[] = [];
  activeOrders: Order[] = [];
  loading = false;
  error = '';
  userId: number = 0;

  stats = {
    totalOrders: 0,
    activeOrders: 0,
    completedOrders: 0,
    totalSpent: 0
  };

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) {
      this.userId = user.id;
    }
  }

  ngOnInit() {
    this.loadDashboardData();
  }

  loadDashboardData() {
    this.loading = true;
    this.error = '';

    // Load profile
    this.userService.getProfile(this.userId).subscribe({
      next: (profile) => {
        this.profile = profile;
      },
      error: (err) => {
        console.error('Error loading profile:', err);
      }
    });

    // Load recent orders
    this.userService.getOrders(this.userId).subscribe({
      next: (orders) => {
        this.recentOrders = orders.slice(0, 5);
        this.activeOrders = orders.filter(o => 
          ['PENDING', 'ACCEPTED', 'PREPARING', 'READY', 'PICKED_UP'].includes(o.orderStatus)
        );
        
        // Calculate stats
        this.stats.totalOrders = orders.length;
        this.stats.activeOrders = this.activeOrders.length;
        this.stats.completedOrders = orders.filter(o => o.orderStatus === 'DELIVERED').length;
        this.stats.totalSpent = orders
          .filter(o => o.orderStatus === 'DELIVERED')
          .reduce((sum, order) => sum + order.totalAmount, 0);
        
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading orders:', err);
        this.error = 'Failed to load orders. Please try again.';
        this.loading = false;
      }
    });
  }

  getStatusClass(status: string): string {
    const statusMap: { [key: string]: string } = {
      'PENDING': 'status-pending',
      'ACCEPTED': 'status-accepted',
      'PREPARING': 'status-preparing',
      'READY': 'status-ready',
      'PICKED_UP': 'status-picked-up',
      'DELIVERED': 'status-delivered',
      'CANCELLED': 'status-cancelled'
    };
    return statusMap[status] || 'status-default';
  }

  getStatusLabel(status: string): string {
    return status.replace('_', ' ');
  }

  trackOrder(orderId: number) {
    // Navigate to order tracking page
    console.log('Track order:', orderId);
  }

  reorder(orderId: number) {
    // Reorder functionality
    console.log('Reorder:', orderId);
  }
}