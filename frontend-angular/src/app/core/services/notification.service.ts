import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, interval } from 'rxjs';
import { tap, switchMap, startWith } from 'rxjs/operators';

export interface Notification {
  id: number;
  title: string;
  message: string;
  type: string;
  referenceId?: number;
  read: boolean;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private apiUrl = 'http://localhost:8082/api/notifications';
  private unreadCountSubject = new BehaviorSubject<number>(0);
  private notificationsSubject = new BehaviorSubject<Notification[]>([]);

  unreadCount$ = this.unreadCountSubject.asObservable();
  notifications$ = this.notificationsSubject.asObservable();

  constructor(private http: HttpClient) {}

  startPolling(): void {
    // Poll every 30 seconds for new notifications
    interval(30000).pipe(
      startWith(0),
      switchMap(() => this.fetchUnreadCount())
    ).subscribe();
  }

  fetchUnreadCount(): Observable<{ count: number }> {
    return this.http.get<{ count: number }>(`${this.apiUrl}/unread-count`).pipe(
      tap(response => this.unreadCountSubject.next(response.count))
    );
  }

  getUnreadNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.apiUrl}/unread`).pipe(
      tap(notifications => this.notificationsSubject.next(notifications))
    );
  }

  getNotifications(page = 0, size = 20): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  markAsRead(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/read`, {}).pipe(
      tap(() => {
        const current = this.unreadCountSubject.value;
        if (current > 0) {
          this.unreadCountSubject.next(current - 1);
        }
        // Update notifications list
        const notifications = this.notificationsSubject.value.map(n => 
          n.id === id ? { ...n, read: true } : n
        );
        this.notificationsSubject.next(notifications);
      })
    );
  }

  markAllAsRead(): Observable<any> {
    return this.http.put(`${this.apiUrl}/read-all`, {}).pipe(
      tap(() => {
        this.unreadCountSubject.next(0);
        const notifications = this.notificationsSubject.value.map(n => ({ ...n, read: true }));
        this.notificationsSubject.next(notifications);
      })
    );
  }

  getNotificationIcon(type: string): string {
    const icons: { [key: string]: string } = {
      'ORDER_PLACED': '📦',
      'ORDER_CONFIRMED': '✅',
      'ORDER_PREPARING': '👨‍🍳',
      'ORDER_READY': '🍽️',
      'ORDER_PICKED_UP': '🚴',
      'ORDER_OUT_FOR_DELIVERY': '🚗',
      'ORDER_DELIVERED': '🎉',
      'ORDER_CANCELLED': '❌',
      'NEW_ORDER_NEARBY': '📍',
      'PAYMENT_RECEIVED': '💰',
      'WITHDRAWAL_PROCESSED': '💳',
      'ACCOUNT_APPROVED': '✅',
      'ACCOUNT_REJECTED': '❌',
      'PROMOTIONAL': '🎁'
    };
    return icons[type] || '🔔';
  }
}
