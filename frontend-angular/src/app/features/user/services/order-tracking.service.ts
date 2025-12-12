import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, interval, Subscription } from 'rxjs';
import { switchMap, takeWhile } from 'rxjs/operators';
import { environment } from '../../../../environments/environment';

export interface OrderTracking {
  orderId: number;
  status: string;
  statusMessage: string;
  stepNumber: number;
  totalSteps: number;
  executiveLatitude?: number;
  executiveLongitude?: number;
  pickupLatitude?: number;
  pickupLongitude?: number;
  deliveryLatitude?: number;
  deliveryLongitude?: number;
  executiveId?: number;
  executiveName?: string;
  executivePhone?: string;
  executivePhoto?: string;
  estimatedPrepTime?: number;
  estimatedDeliveryTime?: number;
  remainingMinutes?: number;
  createdAt: string;
  acceptedAt?: string;
  pickedUpAt?: string;
  deliveredAt?: string;
  lastUpdated: string;
  homemakerName?: string;
  amount: number;
  paymentMethod?: string;
  customerOtp?: string;
}

@Injectable({
  providedIn: 'root'
})
export class OrderTrackingService implements OnDestroy {
  private apiUrl = `${environment.apiUrl}/orders`;
  private trackingSubject = new BehaviorSubject<OrderTracking | null>(null);
  private pollingSubscription?: Subscription;
  
  tracking$ = this.trackingSubject.asObservable();

  constructor(private http: HttpClient) {}

  ngOnDestroy(): void {
    this.stopTracking();
  }

  getOrderTracking(orderId: number): Observable<OrderTracking> {
    return this.http.get<OrderTracking>(`${this.apiUrl}/${orderId}/track`);
  }


  startTracking(orderId: number): void {
    this.stopTracking();
    
    // Initial fetch
    this.getOrderTracking(orderId).subscribe(tracking => {
      this.trackingSubject.next(tracking);
    });
    
    // Poll every 10 seconds for updates
    this.pollingSubscription = interval(10000).pipe(
      switchMap(() => this.getOrderTracking(orderId)),
      takeWhile(tracking => 
        tracking.status !== 'DELIVERED' && tracking.status !== 'CANCELLED', 
        true
      )
    ).subscribe(tracking => {
      this.trackingSubject.next(tracking);
    });
  }

  stopTracking(): void {
    if (this.pollingSubscription) {
      this.pollingSubscription.unsubscribe();
      this.pollingSubscription = undefined;
    }
    this.trackingSubject.next(null);
  }

  getStatusIcon(status: string): string {
    const icons: { [key: string]: string } = {
      'PENDING': '⏳',
      'PREPARING': '👨‍🍳',
      'PREPARED': '✅',
      'ACCEPTED': '🚴',
      'OUTFORDELIVERY': '🛵',
      'DELIVERED': '🎉',
      'CANCELLED': '❌'
    };
    return icons[status] || '📦';
  }

  getStatusColor(status: string): string {
    const colors: { [key: string]: string } = {
      'PENDING': '#FFA500',
      'PREPARING': '#FF8A00',
      'PREPARED': '#2ECC71',
      'ACCEPTED': '#3498DB',
      'OUTFORDELIVERY': '#9B59B6',
      'DELIVERED': '#27AE60',
      'CANCELLED': '#E74C3C'
    };
    return colors[status] || '#666';
  }
}
