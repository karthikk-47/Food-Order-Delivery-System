import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomemakerService, HomemakerProfile, HomemakerAnalytics, HomemakerOrder } from '../../../core/services/homemaker.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-homemaker-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class HomemakerDashboardComponent implements OnInit {
  profile: HomemakerProfile | null = null;
  analytics: HomemakerAnalytics | null = null;
  pendingOrders: HomemakerOrder[] = [];
  activeOrders: HomemakerOrder[] = [];
  loading = false;
  error = '';
  homemakerId: number = 0;

  stats = {
    todayOrders: 0,
    todayRevenue: 0,
    pendingOrders: 0,
    activeOrders: 0
  };

  constructor(
    private homemakerService: HomemakerService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) {
      this.homemakerId = user.id;
    }
  }

  ngOnInit() {
    this.loadDashboardData();
  }

  loadDashboardData() {
    this.loading = true;
    this.error = '';

    // Load profile
    this.homemakerService.getProfile(this.homemakerId).subscribe({
      next: (profile) => {
        this.profile = profile;
      },
      error: (err) => {
        console.error('Error loading profile:', err);
      }
    });

    // Load analytics
    this.homemakerService.getAnalytics(this.homemakerId).subscribe({
      next: (analytics) => {
        this.analytics = analytics;
      },
      error: (err) => {
        console.error('Error loading analytics:', err);
      }
    });

    // Load orders
    this.homemakerService.getOrders(this.homemakerId).subscribe({
      next: (orders) => {
        this.pendingOrders = orders.filter(o => o.orderStatus === 'PENDING');
        this.activeOrders = orders.filter(o => 
          ['ACCEPTED', 'PREPARING', 'READY'].includes(o.orderStatus)
        );

        // Calculate today's stats
        const today = new Date().toDateString();
        const todayOrders = orders.filter(o => 
          new Date(o.orderDate).toDateString() === today
        );
        
        this.stats.todayOrders = todayOrders.length;
        this.stats.todayRevenue = todayOrders.reduce((sum, o) => sum + o.totalAmount, 0);
        this.stats.pendingOrders = this.pendingOrders.length;
        this.stats.activeOrders = this.activeOrders.length;

        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading orders:', err);
        this.error = 'Failed to load orders. Please try again.';
        this.loading = false;
      }
    });
  }

  acceptOrder(orderId: number) {
    this.homemakerService.acceptOrder(orderId).subscribe({
      next: () => {
        this.loadDashboardData();
      },
      error: (err) => {
        console.error('Error accepting order:', err);
        alert('Failed to accept order. Please try again.');
      }
    });
  }

  rejectOrder(orderId: number) {
    const reason = prompt('Please provide a reason for rejection:');
    if (reason) {
      this.homemakerService.rejectOrder(orderId, reason).subscribe({
        next: () => {
          this.loadDashboardData();
        },
        error: (err) => {
          console.error('Error rejecting order:', err);
          alert('Failed to reject order. Please try again.');
        }
      });
    }
  }

  markOrderReady(orderId: number) {
    this.homemakerService.markOrderReady(orderId).subscribe({
      next: () => {
        this.loadDashboardData();
      },
      error: (err) => {
        console.error('Error marking order ready:', err);
        alert('Failed to mark order as ready. Please try again.');
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
}