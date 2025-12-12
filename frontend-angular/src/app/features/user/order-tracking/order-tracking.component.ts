import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { OrderTrackingService, OrderTracking } from '../services/order-tracking.service';
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
  private subscription?: Subscription;

  steps = [
    { status: 'PENDING', label: 'Order Placed', icon: '📝' },
    { status: 'PREPARING', label: 'Preparing', icon: '👨‍🍳' },
    { status: 'PREPARED', label: 'Ready', icon: '✅' },
    { status: 'OUTFORDELIVERY', label: 'On the Way', icon: '🛵' },
    { status: 'DELIVERED', label: 'Delivered', icon: '🎉' }
  ];

  constructor(
    private route: ActivatedRoute,
    public trackingService: OrderTrackingService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.orderId = +params['orderId'];
      this.loadTracking();
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
