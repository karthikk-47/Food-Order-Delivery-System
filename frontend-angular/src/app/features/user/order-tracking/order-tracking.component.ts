import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { OrderTrackingService, OrderTracking } from '../services/order-tracking.service';
import { WebSocketService, LocationUpdateMessage, OrderStatusMessage } from '../../../core/services/websocket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-order-tracking',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './order-tracking.component.html',
  styleUrls: ['./order-tracking.component.scss']
})
export class OrderTrackingComponent implements OnInit, OnDestroy {
  tracking: OrderTracking | null = null;
  loading = true;
  error = '';
  orderId!: number;
  isLiveTracking = false;
  executiveLocation: { lat: number; lng: number } | null = null;
  private subscription?: Subscription;
  private wsSubscriptions: Subscription[] = [];

  steps = [
    { status: 'PENDING', label: 'Order Placed', icon: '📝' },
    { status: 'PREPARING', label: 'Preparing', icon: '👨‍🍳' },
    { status: 'PREPARED', label: 'Ready', icon: '✅' },
    { status: 'OUTFORDELIVERY', label: 'On the Way', icon: '🛵' },
    { status: 'DELIVERED', label: 'Delivered', icon: '🎉' }
  ];

  constructor(
    private route: ActivatedRoute,
    public trackingService: OrderTrackingService,
    private wsService: WebSocketService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.orderId = +params['orderId'];
      this.loadTracking();
      this.setupWebSocket();
    });

    this.subscription = this.trackingService.tracking$.subscribe(tracking => {
      if (tracking) {
        this.tracking = tracking;
        this.loading = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.trackingService.stopTracking();
    this.subscription?.unsubscribe();
    this.wsSubscriptions.forEach(sub => sub.unsubscribe());
    this.wsService.unsubscribeFromOrderTracking(this.orderId);
  }

  private setupWebSocket(): void {
    this.wsService.connect();
    
    this.wsSubscriptions.push(
      this.wsService.isConnected$.subscribe(connected => {
        if (connected) {
          this.wsService.subscribeToOrderTracking(this.orderId);
          this.isLiveTracking = true;
        }
      })
    );

    this.wsSubscriptions.push(
      this.wsService.locationUpdates$.subscribe((location: LocationUpdateMessage) => {
        if (location.orderId === this.orderId) {
          this.executiveLocation = { lat: location.latitude, lng: location.longitude };
          if (this.tracking) {
            this.tracking.executiveLatitude = location.latitude;
            this.tracking.executiveLongitude = location.longitude;
            if (location.estimatedArrival) {
              // Parse estimated arrival time to minutes if it's a string
              const arrivalStr = location.estimatedArrival;
              const minutes = parseInt(arrivalStr, 10);
              if (!isNaN(minutes)) {
                this.tracking.estimatedDeliveryTime = minutes;
              }
            }
          }
        }
      })
    );

    this.wsSubscriptions.push(
      this.wsService.orderStatusUpdates$.subscribe((status: OrderStatusMessage) => {
        if (status.orderId === this.orderId && this.tracking) {
          this.tracking.status = status.status;
          this.tracking.stepNumber = this.getStepNumber(status.status);
          if (status.executiveName) {
            this.tracking.executiveName = status.executiveName;
          }
        }
      })
    );
  }

  private getStepNumber(status: string): number {
    const statusMap: { [key: string]: number } = {
      'PENDING': 1,
      'PREPARING': 2,
      'PREPARED': 3,
      'OUTFORDELIVERY': 4,
      'DELIVERED': 5
    };
    return statusMap[status] || 1;
  }

  loadTracking(): void {
    this.loading = true;
    this.trackingService.startTracking(this.orderId);
  }


  isStepCompleted(stepIndex: number): boolean {
    if (!this.tracking) return false;
    return this.tracking.stepNumber > stepIndex + 1;
  }

  isStepActive(stepIndex: number): boolean {
    if (!this.tracking) return false;
    return this.tracking.stepNumber === stepIndex + 1;
  }

  getProgressWidth(): string {
    if (!this.tracking) return '0%';
    const progress = ((this.tracking.stepNumber - 1) / (this.steps.length - 1)) * 100;
    return `${Math.min(progress, 100)}%`;
  }

  formatTime(dateString: string | undefined): string {
    if (!dateString) return '--:--';
    const date = new Date(dateString);
    return date.toLocaleTimeString('en-IN', { hour: '2-digit', minute: '2-digit' });
  }

  callExecutive(): void {
    if (this.tracking?.executivePhone) {
      window.location.href = `tel:${this.tracking.executivePhone}`;
    }
  }

  refreshTracking(): void {
    this.loadTracking();
  }
}
