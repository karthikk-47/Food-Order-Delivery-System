import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-logs-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NavbarComponent],
  templateUrl: './logs-dashboard.component.html',
  styleUrls: ['./logs-dashboard.component.scss']
})
export class LogsDashboardComponent implements OnInit {
  logs: any[] = [];
  statistics: any = {};
  loading = false;
  error = '';
  
  // Pagination
  currentPage = 0;
  pageSize = 50;
  totalPages = 0;
  totalElements = 0;
  
  // Filters
  selectedLevel = '';
  selectedCategory = '';
  searchQuery = '';
  
  levels = ['INFO', 'WARNING', 'ERROR', 'DEBUG'];
  categories = ['AUTH', 'USER', 'HOMEMAKER', 'EXECUTIVE', 'ORDER', 'PAYMENT', 'APPROVAL', 'SYSTEM', 'ADMIN'];

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadLogs();
    this.loadStatistics();
  }

  loadLogs() {
    this.loading = true;
    this.error = '';

    this.adminService.getLogs(
      this.currentPage, 
      this.pageSize, 
      this.selectedLevel, 
      this.selectedCategory, 
      this.searchQuery
    ).subscribe({
      next: (response) => {
        this.logs = response.content || [];
        this.totalPages = response.totalPages || 0;
        this.totalElements = response.totalElements || 0;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading logs:', err);
        this.error = 'Failed to load logs';
        this.loading = false;
      }
    });
  }

  loadStatistics() {
    this.adminService.getLogStatistics().subscribe({
      next: (stats) => {
        this.statistics = stats;
      },
      error: (err) => console.error('Error loading statistics:', err)
    });
  }

  applyFilters() {
    this.currentPage = 0;
    this.loadLogs();
  }

  clearFilters() {
    this.selectedLevel = '';
    this.selectedCategory = '';
    this.searchQuery = '';
    this.currentPage = 0;
    this.loadLogs();
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadLogs();
    }
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadLogs();
    }
  }

  goToPage(page: number) {
    this.currentPage = page;
    this.loadLogs();
  }

  refreshLogs() {
    this.loadLogs();
    this.loadStatistics();
  }

  getLevelClass(level: string): string {
    switch (level) {
      case 'ERROR': return 'level-error';
      case 'WARNING': return 'level-warning';
      case 'INFO': return 'level-info';
      case 'DEBUG': return 'level-debug';
      default: return '';
    }
  }

  getCategoryIcon(category: string): string {
    switch (category) {
      case 'AUTH': return '🔐';
      case 'USER': return '👤';
      case 'HOMEMAKER': return '👨‍🍳';
      case 'EXECUTIVE': return '🚗';
      case 'ORDER': return '📦';
      case 'PAYMENT': return '💳';
      case 'APPROVAL': return '✅';
      case 'SYSTEM': return '⚙️';
      case 'ADMIN': return '👑';
      default: return '📋';
    }
  }

  formatTimestamp(timestamp: string): string {
    return new Date(timestamp).toLocaleString();
  }

  getPageNumbers(): number[] {
    const pages: number[] = [];
    const start = Math.max(0, this.currentPage - 2);
    const end = Math.min(this.totalPages, start + 5);
    
    for (let i = start; i < end; i++) {
      pages.push(i);
    }
    return pages;
  }
}
