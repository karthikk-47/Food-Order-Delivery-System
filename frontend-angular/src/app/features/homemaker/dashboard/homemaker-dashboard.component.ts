import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomemakerService } from '../services/homemaker.service';

@Component({
  selector: 'app-homemaker-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers: [HomemakerService],
  templateUrl: './homemaker-dashboard.component.html',
  styleUrls: ['./homemaker-dashboard.component.scss']
})
export class HomemakerDashboardComponent implements OnInit {
  stats = {
    totalOrders: 0,
    activeOrders: 0,
    totalRevenue: 0,
    averageRating: 0,
    totalMenuItems: 0,
    completedOrders: 0
  };
  recentOrders: any[] = [];
  topMenuItems: any[] = [];
  loading = false;

  constructor(private homemakerService: HomemakerService) {}

  ngOnInit() {
    this.loadDashboard();
  }

  loadDashboard() {
    this.loading = true;
    
    this.homemakerService.getDashboardStats().subscribe({
      next: (stats: any) => {
        this.stats = stats;
        this.loading = false;
      },
      error: (err: any) => {
        console.error('Error loading stats:', err);
        this.loading = false;
      }
    });

    this.homemakerService.getRecentOrders().subscribe({
      next: (orders: any) => {
        this.recentOrders = orders;
      },
      error: (err: any) => console.error('Error loading orders:', err)
    });

    this.homemakerService.getTopMenuItems().subscribe({
      next: (items: any) => {
        this.topMenuItems = items;
      },
      error: (err: any) => console.error('Error loading menu items:', err)
    });
  }

  acceptOrder(orderId: number) {
    this.homemakerService.acceptOrder(orderId).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err: any) => console.error('Error accepting order:', err)
    });
  }

  markAsReady(orderId: number) {
    this.homemakerService.markOrderReady(orderId).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err: any) => console.error('Error marking order ready:', err)
    });
  }
}
