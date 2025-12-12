import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  stats: any = {
    totalUsers: 0,
    totalHomemakers: 0,
    totalExecutives: 0,
    totalOrders: 0,
    totalRevenue: 0,
    activeOrders: 0,
    pendingVerifications: 0,
    pendingHomemakers: 0,
    pendingExecutives: 0,
    openDisputes: 0
  };
  recentUsers: any[] = [];
  pendingHomemakers: any[] = [];
  pendingExecutives: any[] = [];
  recentDisputes: any[] = [];
  loading = false;
  rejectionReason = '';

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadDashboard();
  }

  loadDashboard() {
    this.loading = true;
    
    this.adminService.getDashboardStats().subscribe({
      next: (stats) => {
        this.stats = stats;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading stats:', err);
        this.loading = false;
      }
    });

    this.adminService.getAllUsers().subscribe({
      next: (users) => {
        this.recentUsers = users.slice(0, 5);
      },
      error: (err) => console.error('Error loading users:', err)
    });

    this.adminService.getAllDisputes().subscribe({
      next: (disputes) => {
        this.recentDisputes = disputes.filter((d: any) => d.status === 'OPEN').slice(0, 5);
      },
      error: (err) => console.error('Error loading disputes:', err)
    });

    // Load pending approvals
    this.adminService.getPendingHomemakerApprovals().subscribe({
      next: (homemakers) => {
        this.pendingHomemakers = homemakers;
      },
      error: (err) => console.error('Error loading pending homemakers:', err)
    });

    this.adminService.getPendingExecutiveApprovals().subscribe({
      next: (executives) => {
        this.pendingExecutives = executives;
      },
      error: (err) => console.error('Error loading pending executives:', err)
    });
  }

  verifyUser(userId: number) {
    this.adminService.verifyUser(userId).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err) => console.error('Error verifying user:', err)
    });
  }

  approveHomemaker(id: number) {
    this.adminService.approveHomemaker(id).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err) => console.error('Error approving homemaker:', err)
    });
  }

  rejectHomemaker(id: number, reason: string) {
    if (!reason) {
      reason = 'Application rejected by admin';
    }
    this.adminService.rejectHomemaker(id, reason).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err) => console.error('Error rejecting homemaker:', err)
    });
  }

  approveExecutive(id: number) {
    this.adminService.approveExecutive(id).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err) => console.error('Error approving executive:', err)
    });
  }

  rejectExecutive(id: number, reason: string) {
    if (!reason) {
      reason = 'Application rejected by admin';
    }
    this.adminService.rejectExecutive(id, reason).subscribe({
      next: () => {
        this.loadDashboard();
      },
      error: (err) => console.error('Error rejecting executive:', err)
    });
  }

  resolveDispute(disputeId: number) {
    // Navigate to dispute resolution page
  }
}
