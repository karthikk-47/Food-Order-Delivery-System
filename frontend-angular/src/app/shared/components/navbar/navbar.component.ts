import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService, User } from '../../../core/services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  currentUser: User | null = null;
  menuOpen = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  closeMenu(): void {
    this.menuOpen = false;
  }

  logout(): void {
    this.authService.logout();
    this.menuOpen = false;
  }

  getDashboardLink(): string {
    if (!this.currentUser) return '/';
    
    switch (this.currentUser.role) {
      case 'USER':
        return '/user/dashboard';
      case 'HOMEMAKER':
        return '/homemaker/dashboard';
      case 'DELIVERYEXECUTIVE':
        return '/delivery-executive/dashboard';
      case 'ADMIN':
        return '/admin/dashboard';
      default:
        return '/';
    }
  }

  getProfileLink(): string {
    if (!this.currentUser) return '/';
    
    switch (this.currentUser.role) {
      case 'USER':
        return '/user/profile';
      case 'HOMEMAKER':
        return '/homemaker/profile';
      case 'DELIVERYEXECUTIVE':
        return '/delivery-executive/profile';
      case 'ADMIN':
        return '/admin/dashboard'; // Admin may not have a profile page
      default:
        return '/';
    }
  }

  getRoleLabel(): string {
    if (!this.currentUser) return '';
    
    const roles: { [key: string]: string } = {
      'USER': 'Customer',
      'HOMEMAKER': 'Homemaker',
      'DELIVERYEXECUTIVE': 'Delivery Executive',
      'ADMIN': 'Admin'
    };
    return roles[this.currentUser.role] || this.currentUser.role;
  }
}
