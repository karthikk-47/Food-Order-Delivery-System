import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-user-management',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {
  users: any[] = [];
  filteredUsers: any[] = [];
  searchQuery = '';
  loading = false;
  error = '';
  success = '';
  currentPage = 0;
  pageSize = 10;
  totalRecords = 0;
  totalPages = 0;
  selectedUser: any = null;
  deleteConfirm = false;
  deleteUserId: number | null = null;

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.loading = true;
    this.error = '';
    
    this.adminService.getUsers(this.searchQuery, this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.users = response.data || [];
        this.totalRecords = response.totalRecords || 0;
        this.totalPages = response.totalPages || 0;
        this.filteredUsers = this.users;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading users:', err);
        this.error = 'Failed to load users';
        this.loading = false;
      }
    });
  }

  search() {
    this.currentPage = 0;
    this.loadUsers();
  }

  onSearchChange() {
    this.currentPage = 0;
    this.loadUsers();
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadUsers();
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadUsers();
    }
  }

  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.loadUsers();
    }
  }

  viewUserDetails(user: any) {
    this.selectedUser = user;
  }

  closeDetails() {
    this.selectedUser = null;
  }

  openDeleteConfirm(userId: number) {
    this.deleteUserId = userId;
    this.deleteConfirm = true;
  }

  closeDeleteConfirm() {
    this.deleteConfirm = false;
    this.deleteUserId = null;
  }

  confirmDelete() {
    if (this.deleteUserId) {
      this.loading = true;
      this.adminService.deleteUser(this.deleteUserId).subscribe({
        next: () => {
          this.success = 'User deleted successfully';
          this.closeDeleteConfirm();
          this.loadUsers();
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.error('Error deleting user:', err);
          this.error = 'Failed to delete user';
          this.loading = false;
        }
      });
    }
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
