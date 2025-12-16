import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService } from '../services/user.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  activeOrders: any[] = [];
  nearbyHomemakers: any[] = [];
  stats = {
    totalOrders: 0,
    activeOrders: 0,
    totalSpent: 0
  };
  loading = false;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadDashboard();
  }

  loadDashboard() {
    this.loading = true;
    this.userService.getActiveOrders().subscribe({
      next: (orders) => {
        this.activeOrders = orders;
        this.stats.activeOrders = orders.length;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading orders:', err);
        this.loading = false;
      }
    });

    this.userService.getNearbyHomemakers().subscribe({
      next: (homemakers) => {
        this.nearbyHomemakers = homemakers;
      },
      error: (err) => console.error('Error loading homemakers:', err)
    });

    this.userService.getOrderStats().subscribe({
      next: (stats) => {
        this.stats = { ...this.stats, ...stats };
      },
      error: (err) => console.error('Error loading stats:', err)
    });
  }

  trackOrder(orderId: number) {
    // Navigate to order tracking
  }
}
