import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminService, AdminDashboardStats, Verification, Dispute, ProfileApproval, ApprovalStatistics } from '../../../core/services/admin.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  stats: AdminDashboardStats | null = null;
  approvalStats: ApprovalStatistics | null = null;
  pendingVerifications: Verification[] = [];
  openDisputes: Dispute[] = [];
  pendingHomemakers: ProfileApproval[] = [];
  pendingExecutives: ProfileApproval[] = [];
  loading = false;
  error = '';
  rejectionReason = '';
  selectedProfileId: number | null = null;
  selectedProfileType: 'homemaker' | 'executive' | null = null;

  constructor(
    private adminService: AdminService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.loadDashboardData();
  }

  loadDashboardData() {
    this.loading = true;
    this.error = '';

    // Load dashboard stats
    this.adminService.getDashboardStats().subscribe({
      next: (stats) => {
        this.stats = stats;
      },
      error: (err) => {
        console.error('Error loading stats:', err);
      }
    });

    // Load approval statistics
    this.adminService.getApprovalStatistics().subscribe({
      next: (stats) => {
        this.approvalStats = stats;
      },
      error: (err) => {
        console.error('Error loading approval stats:', err);
      }
    });

    // Load pending homemaker approvals
    this.adminService.getPendingHomemakerApprovals().subscribe({
      next: (approvals) => {
        this.pendingHomemakers = approvals.slice(0, 5);
      },
      error: (err) => {
        console.error('Error loading pending homemakers:', err);
      }
    });

    // Load pending executive approvals
    this.adminService.getPendingExecutiveApprovals().subscribe({
      next: (approvals) => {
        this.pendingExecutives = approvals.slice(0, 5);
      },
      error: (err) => {
        console.error('Error loading pending executives:', err);
      }
    });

    // Load pending verifications
    this.adminService.getVerifications('PENDING').subscribe({
      next: (verifications) => {
        this.pendingVerifications = verifications.slice(0, 5);
      },
      error: (err) => {
        console.error('Error loading verifications:', err);
      }
    });

    // Load open disputes
    this.adminService.getDisputes('OPEN').subscribe({
      next: (disputes) => {
        this.openDisputes = disputes.slice(0, 5);
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading disputes:', err);
        this.error = 'Failed to load dashboard data. Please try again.';
        this.loading = false;
      }
    });
  }

  // Profile Approval Actions
  approveHomemaker(id: number) {
    this.adminService.approveHomemaker(id).subscribe({
      next: () => {
        this.pendingHomemakers = this.pendingHomemakers.filter(h => h.id !== id);
        this.loadDashboardData(); // Refresh stats
      },
      error: (err) => {
        console.error('Error approving homemaker:', err);
        this.error = 'Failed to approve homemaker. Please try again.';
      }
    });
  }

  approveExecutive(id: number) {
    this.adminService.approveExecutive(id).subscribe({
      next: () => {
        this.pendingExecutives = this.pendingExecutives.filter(e => e.id !== id);
        this.loadDashboardData(); // Refresh stats
      },
      error: (err) => {
        console.error('Error approving executive:', err);
        this.error = 'Failed to approve executive. Please try again.';
      }
    });
  }

  openRejectModal(id: number, type: 'homemaker' | 'executive') {
    this.selectedProfileId = id;
    this.selectedProfileType = type;
    this.rejectionReason = '';
  }

  confirmReject() {
    if (!this.selectedProfileId || !this.selectedProfileType || !this.rejectionReason.trim()) {
      return;
    }

    if (this.selectedProfileType === 'homemaker') {
      this.adminService.rejectHomemaker(this.selectedProfileId, this.rejectionReason).subscribe({
        next: () => {
          this.pendingHomemakers = this.pendingHomemakers.filter(h => h.id !== this.selectedProfileId);
          this.closeRejectModal();
          this.loadDashboardData();
        },
        error: (err) => {
          console.error('Error rejecting homemaker:', err);
          this.error = 'Failed to reject homemaker. Please try again.';
        }
      });
    } else {
      this.adminService.rejectExecutive(this.selectedProfileId, this.rejectionReason).subscribe({
        next: () => {
          this.pendingExecutives = this.pendingExecutives.filter(e => e.id !== this.selectedProfileId);
          this.closeRejectModal();
          this.loadDashboardData();
        },
        error: (err) => {
          console.error('Error rejecting executive:', err);
          this.error = 'Failed to reject executive. Please try again.';
        }
      });
    }
  }

  closeRejectModal() {
    this.selectedProfileId = null;
    this.selectedProfileType = null;
    this.rejectionReason = '';
  }

  getVerificationTypeLabel(type: string): string {
    const labels: { [key: string]: string } = {
      'IDENTITY': 'Identity',
      'FSSAI': 'FSSAI License',
      'BANK_ACCOUNT': 'Bank Account',
      'ADDRESS': 'Address'
    };
    return labels[type] || type;
  }

  getDisputeTypeLabel(type: string): string {
    const labels: { [key: string]: string } = {
      'ORDER_NOT_DELIVERED': 'Order Not Delivered',
      'WRONG_ORDER': 'Wrong Order',
      'QUALITY_ISSUE': 'Quality Issue',
      'PAYMENT_ISSUE': 'Payment Issue',
      'REFUND_REQUEST': 'Refund Request'
    };
    return labels[type] || type;
  }

  getStatusClass(status: string): string {
    const statusMap: { [key: string]: string } = {
      'PENDING': 'status-pending',
      'OPEN': 'status-open',
      'IN_PROGRESS': 'status-in-progress',
      'RESOLVED': 'status-resolved',
      'APPROVED': 'status-approved',
      'REJECTED': 'status-rejected'
    };
    return statusMap[status] || 'status-default';
  }

  getApprovalStatusClass(status: string): string {
    switch (status) {
      case 'PENDING': return 'badge-warning';
      case 'APPROVED': return 'badge-success';
      case 'REJECTED': return 'badge-danger';
      default: return 'badge-secondary';
    }
  }
}