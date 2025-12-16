import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService, User } from '../../../core/services/auth.service';
import { NotificationService, Notification } from '../../../core/services/notification.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {
  currentUser: User | null = null;
  menuOpen = false;
  notificationOpen = false;
  unreadCount = 0;
  notifications: Notification[] = [];
  private subscriptions: Subscription[] = [];

  constructor(
    private authService: AuthService,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.authService.currentUser.subscribe(user => {
        this.currentUser = user;
        if (user) {
          this.loadNotifications();
          this.notificationService.startPolling();
        }
      })
    );

    this.subscriptions.push(
      this.notificationService.unreadCount$.subscribe(count => {
        this.unreadCount = count;
      })
    );

    this.subscriptions.push(
      this.notificationService.notifications$.subscribe(notifications => {
        this.notifications = notifications;
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event): void {
    const target = event.target as HTMLElement;
    if (!target.closest('.notification-wrapper')) {
      this.notificationOpen = false;
    }
  }

  loadNotifications(): void {
    this.notificationService.fetchUnreadCount().subscribe();
    this.notificationService.getUnreadNotifications().subscribe();
  }

  toggleNotifications(): void {
    this.notificationOpen = !this.notificationOpen;
    if (this.notificationOpen) {
      this.notificationService.getUnreadNotifications().subscribe();
    }
  }

  markAsRead(notification: Notification): void {
    if (!notification.read) {
      this.notificationService.markAsRead(notification.id).subscribe();
    }
  }

  markAllAsRead(): void {
    this.notificationService.markAllAsRead().subscribe();
  }

  getNotificationIcon(type: string): string {
    return this.notificationService.getNotificationIcon(type);
  }

  getTimeAgo(dateString: string): string {
    const date = new Date(dateString);
    const now = new Date();
    const seconds = Math.floor((now.getTime() - date.getTime()) / 1000);

    if (seconds < 60) return 'Just now';
    if (seconds < 3600) return `${Math.floor(seconds / 60)}m ago`;
    if (seconds < 86400) return `${Math.floor(seconds / 3600)}h ago`;
    return `${Math.floor(seconds / 86400)}d ago`;
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
        return '/admin/dashboard';
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
