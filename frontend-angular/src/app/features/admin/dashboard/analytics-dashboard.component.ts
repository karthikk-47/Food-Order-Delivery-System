import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-analytics-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './analytics-dashboard.component.html',
  styleUrls: ['./analytics-dashboard.component.scss']
})
export class AnalyticsDashboardComponent implements OnInit {
  dashboardData: any = null;
  userAnalytics: any = null;
  homemakerAnalytics: any = null;
  executiveAnalytics: any = null;
  orderAnalytics: any = null;
  loading = false;
  error = '';

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadAllAnalytics();
  }

  loadAllAnalytics() {
    this.loading = true;
    this.error = '';

    // Load dashboard analytics
    this.adminService.getDashboardAnalytics().subscribe({
      next: (data) => {
        this.dashboardData = data;
      },
      error: (err) => {
        console.error('Error loading dashboard analytics:', err);
        this.error = 'Failed to load analytics';
      }
    });

    // Load user analytics
    this.adminService.getUserAnalytics().subscribe({
      next: (data) => {
        this.userAnalytics = data;
      },
      error: (err) => console.error('Error loading user analytics:', err)
    });

    // Load homemaker analytics
    this.adminService.getHomemakerAnalytics().subscribe({
      next: (data) => {
        this.homemakerAnalytics = data;
      },
      error: (err) => console.error('Error loading homemaker analytics:', err)
    });

    // Load executive analytics
    this.adminService.getExecutiveAnalytics().subscribe({
      next: (data) => {
        this.executiveAnalytics = data;
      },
      error: (err) => console.error('Error loading executive analytics:', err)
    });

    // Load order analytics
    this.adminService.getOrderAnalytics().subscribe({
      next: (data) => {
        this.orderAnalytics = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading order analytics:', err);
        this.loading = false;
      }
    });
  }

  getApprovalPercentage(approved: number, total: number): number {
    return total > 0 ? Math.round((approved / total) * 100) : 0;
  }
}
