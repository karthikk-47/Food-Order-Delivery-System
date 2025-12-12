import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-executive-management',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  templateUrl: './executive-management.component.html',
  styleUrls: ['./executive-management.component.scss']
})
export class ExecutiveManagementComponent implements OnInit {
  executives: any[] = [];
  searchQuery = '';
  statusFilter = '';
  loading = false;
  error = '';
  success = '';
  currentPage = 0;
  pageSize = 10;
  totalRecords = 0;
  totalPages = 0;
  selectedExecutive: any = null;
  rejectModal = false;
  rejectReason = '';
  rejectId: number | null = null;
  deleteConfirm = false;
  deleteId: number | null = null;

  statusOptions = ['', 'PENDING', 'APPROVED', 'REJECTED'];

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadExecutives();
  }

  loadExecutives() {
    this.loading = true;
    this.error = '';
    
    this.adminService.getExecutives(this.statusFilter, this.searchQuery, this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.executives = response.data || [];
        this.totalRecords = response.totalRecords || 0;
        this.totalPages = response.totalPages || 0;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading executives:', err);
        this.error = 'Failed to load executives';
        this.loading = false;
      }
    });
  }

  onFilterChange() {
    this.currentPage = 0;
    this.loadExecutives();
  }

  onSearchChange() {
    this.currentPage = 0;
    this.loadExecutives();
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadExecutives();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadExecutives();
    }
  }

  viewDetails(executive: any) {
    this.selectedExecutive = executive;
  }

  closeDetails() {
    this.selectedExecutive = null;
  }

  approveExecutive(id: number) {
    this.loading = true;
    this.adminService.updateExecutiveApprovalStatus(id, 'APPROVED').subscribe({
      next: () => {
        this.success = 'Executive approved successfully';
        this.loadExecutives();
        setTimeout(() => this.success = '', 3000);
      },
      error: (err) => {
        console.error('Error approving executive:', err);
        this.error = 'Failed to approve executive';
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
      this.adminService.updateExecutiveApprovalStatus(this.rejectId, 'REJECTED', this.rejectReason).subscribe({
        next: () => {
          this.success = 'Executive rejected successfully';
          this.closeRejectModal();
          this.loadExecutives();
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.error('Error rejecting executive:', err);
          this.error = 'Failed to reject executive';
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
      this.adminService.deleteExecutive(this.deleteId).subscribe({
        next: () => {
          this.success = 'Executive deleted successfully';
          this.closeDeleteConfirm();
          this.loadExecutives();
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.error('Error deleting executive:', err);
          this.error = 'Failed to delete executive';
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
