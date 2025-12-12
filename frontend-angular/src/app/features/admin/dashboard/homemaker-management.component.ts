import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-homemaker-management',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  templateUrl: './homemaker-management.component.html',
  styleUrls: ['./homemaker-management.component.scss']
})
export class HomemakerManagementComponent implements OnInit {
  homemakers: any[] = [];
  searchQuery = '';
  statusFilter = '';
  loading = false;
  error = '';
  success = '';
  currentPage = 0;
  pageSize = 10;
  totalRecords = 0;
  totalPages = 0;
  selectedHomemaker: any = null;
  rejectModal = false;
  rejectReason = '';
  rejectId: number | null = null;
  deleteConfirm = false;
  deleteId: number | null = null;

  statusOptions = ['', 'PENDING', 'APPROVED', 'REJECTED'];

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadHomemakers();
  }

  loadHomemakers() {
    this.loading = true;
    this.error = '';
    
    this.adminService.getHomemakers(this.statusFilter, this.searchQuery, this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.homemakers = response.data || [];
        this.totalRecords = response.totalRecords || 0;
        this.totalPages = response.totalPages || 0;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading homemakers:', err);
        this.error = 'Failed to load homemakers';
        this.loading = false;
      }
    });
  }

  onFilterChange() {
    this.currentPage = 0;
    this.loadHomemakers();
  }

  onSearchChange() {
    this.currentPage = 0;
    this.loadHomemakers();
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadHomemakers();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadHomemakers();
    }
  }

  viewDetails(homemaker: any) {
    this.selectedHomemaker = homemaker;
  }

  closeDetails() {
    this.selectedHomemaker = null;
  }

  approveHomemaker(id: number) {
    this.loading = true;
    this.adminService.updateHomemakerApprovalStatus(id, 'APPROVED').subscribe({
      next: () => {
        this.success = 'Homemaker approved successfully';
        this.loadHomemakers();
        setTimeout(() => this.success = '', 3000);
      },
      error: (err) => {
        console.error('Error approving homemaker:', err);
        this.error = 'Failed to approve homemaker';
        this.loading = false;
      }
    });
  }

  openRejectModal(id: number) {
    this.rejectId = id;
    this.rejectReason = '';
    this.rejectModal = true;
  }

  closeRejectModal() {
    this.rejectModal = false;
    this.rejectId = null;
    this.rejectReason = '';
  }

  confirmReject() {
    if (this.rejectId && this.rejectReason.trim()) {
      this.loading = true;
      this.adminService.updateHomemakerApprovalStatus(this.rejectId, 'REJECTED', this.rejectReason).subscribe({
        next: () => {
          this.success = 'Homemaker rejected successfully';
          this.closeRejectModal();
          this.loadHomemakers();
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.error('Error rejecting homemaker:', err);
          this.error = 'Failed to reject homemaker';
          this.loading = false;
        }
      });
    }
  }

  openDeleteConfirm(id: number) {
    this.deleteId = id;
    this.deleteConfirm = true;
  }

  closeDeleteConfirm() {
    this.deleteConfirm = false;
    this.deleteId = null;
  }

  confirmDelete() {
    if (this.deleteId) {
      this.loading = true;
      this.adminService.deleteHomemaker(this.deleteId).subscribe({
        next: () => {
          this.success = 'Homemaker deleted successfully';
          this.closeDeleteConfirm();
          this.loadHomemakers();
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.error('Error deleting homemaker:', err);
          this.error = 'Failed to delete homemaker';
          this.loading = false;
        }
      });
    }
  }

  getStatusBadgeClass(status: string): string {
    const statusMap: { [key: string]: string } = {
      'PENDING': 'badge-warning',
      'APPROVED': 'badge-success',
      'REJECTED': 'badge-danger'
    };
    return statusMap[status] || 'badge-default';
  }

  getPageNumbers(): number[] {
    const pages = [];
    const maxPages = 5;
    let start = Math.max(0, this.currentPage - Math.floor(maxPages / 2));
    let end = Math.min(this.totalPages, start + maxPages);
    
    if (end - start < maxPages) {
      start = Math.max(0, end - maxPages);
    }
    
    for (let i = start; i < end; i++) {
      pages.push(i);
    }
    
    return pages;
  }
}
