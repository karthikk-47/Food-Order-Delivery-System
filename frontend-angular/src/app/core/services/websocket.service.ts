import { Injectable, OnDestroy } from '@angular/core';
import { Client, IMessage, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AuthService } from './auth.service';

export interface OrderStatusMessage {
  orderId: number;
  status: string;
  previousStatus?: string;
  message?: string;
  executiveId?: number;
  executiveName?: string;
  executiveLatitude?: number;
  executiveLongitude?: number;
  estimatedTime?: string;
  timestamp: string;
}

export interface LocationUpdateMessage {
  orderId: number;
  executiveId: number;
  latitude: number;
  longitude: number;
  speed?: number;
  heading?: number;
  estimatedArrival?: string;
  distanceRemaining?: number;
  timestamp: string;
}

export interface NotificationMessage {
  id: number;
  type: string;
  title: string;
  message: string;
  targetUserId: number;
  targetRole: string;
  relatedOrderId?: number;
  actionUrl?: string;
  read: boolean;
  timestamp: string;
}

export interface NewOrderMessage {
  orderId: number;
  homemakerId: number;
  homemakerName: string;
  pickupLatitude: number;
  pickupLongitude: number;
  pickupAddress: string;
  deliveryLatitude: number;
  deliveryLongitude: number;
  deliveryAddress: string;
  distance: number;
  deliveryFee: number;
  priority: string;
  timestamp: string;
}

@Injectable({
  providedIn: 'root'
})
export class WebSocketService implements OnDestroy {
  private client: Client | null = null;
  private subscriptions: Map<string, StompSubscription> = new Map();
  
  private connectionStatus$ = new BehaviorSubject<boolean>(false);
  private orderStatus$ = new Subject<OrderStatusMessage>();
  private locationUpdate$ = new Subject<LocationUpdateMessage>();
  private notification$ = new Subject<NotificationMessage>();
  private newOrder$ = new Subject<NewOrderMessage>();

  constructor(private authService: AuthService) {}

  get isConnected$(): Observable<boolean> {
    return this.connectionStatus$.asObservable();
  }

  get orderStatusUpdates$(): Observable<OrderStatusMessage> {
    return this.orderStatus$.asObservable();
  }

  get locationUpdates$(): Observable<LocationUpdateMessage> {
    return this.locationUpdate$.asObservable();
  }

  get notifications$(): Observable<NotificationMessage> {
    return this.notification$.asObservable();
  }

  get newOrders$(): Observable<NewOrderMessage> {
    return this.newOrder$.asObservable();
  }

  connect(): void {
    if (this.client?.connected) {
      return;
    }

    const user = this.authService.currentUserValue;
    if (!user) {
      console.warn('Cannot connect WebSocket: No authenticated user');
      return;
    }

    const wsUrl = environment.apiUrl.replace('/api', '') + '/ws';
    
    this.client = new Client({
      webSocketFactory: () => new SockJS(wsUrl),
      connectHeaders: {
        Authorization: `Bearer ${this.authService.getToken()}`
      },
      debug: (str) => {
        if (!environment.production) {
          console.log('STOMP: ' + str);
        }
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    this.client.onConnect = () => {
      console.log('WebSocket connected');
      this.connectionStatus$.next(true);
      this.subscribeToUserQueues();
    };

    this.client.onDisconnect = () => {
      console.log('WebSocket disconnected');
      this.connectionStatus$.next(false);
    };

    this.client.onStompError = (frame) => {
      console.error('STOMP error:', frame.headers['message']);
      console.error('Details:', frame.body);
    };

    this.client.activate();
  }

  disconnect(): void {
    this.subscriptions.forEach((sub) => sub.unsubscribe());
    this.subscriptions.clear();
    
    if (this.client) {
      this.client.deactivate();
      this.client = null;
    }
    this.connectionStatus$.next(false);
  }

  private subscribeToUserQueues(): void {
    const user = this.authService.currentUserValue;
    if (!user || !this.client) return;

    const userPrefix = `/user/${user.id}_${user.role}`;

    // Subscribe to order updates
    this.subscribe(`${userPrefix}/queue/orders/updates`, (message) => {
      const data = JSON.parse(message.body) as OrderStatusMessage;
      this.orderStatus$.next(data);
    });

    // Subscribe to notifications
    this.subscribe(`${userPrefix}/queue/notifications`, (message) => {
      const data = JSON.parse(message.body) as NotificationMessage;
      this.notification$.next(data);
    });

    // For delivery executives: subscribe to new orders
    if (user.role === 'DELIVERYEXECUTIVE') {
      this.subscribe('/topic/executives/new-orders', (message) => {
        const data = JSON.parse(message.body) as NewOrderMessage;
        this.newOrder$.next(data);
      });

      this.subscribe(`${userPrefix}/queue/new-orders`, (message) => {
        const data = JSON.parse(message.body) as NewOrderMessage;
        this.newOrder$.next(data);
      });
    }
  }

  subscribeToOrderTracking(orderId: number): void {
    if (!this.client?.connected) {
      console.warn('WebSocket not connected');
      return;
    }

    // Subscribe to order status updates
    this.subscribe(`/topic/order/${orderId}/status`, (message) => {
      const data = JSON.parse(message.body) as OrderStatusMessage;
      this.orderStatus$.next(data);
    });

    // Subscribe to location updates
    this.subscribe(`/topic/order/${orderId}/location`, (message) => {
      const data = JSON.parse(message.body) as LocationUpdateMessage;
      this.locationUpdate$.next(data);
    });
  }

  unsubscribeFromOrderTracking(orderId: number): void {
    this.unsubscribe(`/topic/order/${orderId}/status`);
    this.unsubscribe(`/topic/order/${orderId}/location`);
  }

  sendLocationUpdate(orderId: number, latitude: number, longitude: number): void {
    if (!this.client?.connected) {
      console.warn('WebSocket not connected');
      return;
    }

    const user = this.authService.currentUserValue;
    if (!user) return;

    this.client.publish({
      destination: `/app/location/${orderId}`,
      body: JSON.stringify({
        executiveId: user.id,
        latitude,
        longitude,
        timestamp: new Date().toISOString()
      })
    });
  }

  private subscribe(destination: string, callback: (message: IMessage) => void): void {
    if (!this.client?.connected) return;
    
    if (this.subscriptions.has(destination)) {
      return;
    }

    const subscription = this.client.subscribe(destination, callback);
    this.subscriptions.set(destination, subscription);
  }

  private unsubscribe(destination: string): void {
    const subscription = this.subscriptions.get(destination);
    if (subscription) {
      subscription.unsubscribe();
      this.subscriptions.delete(destination);
    }
  }

  ngOnDestroy(): void {
    this.disconnect();
  }
}
