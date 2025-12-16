import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

export interface OrderSummary {
  orderId: number;
  orderStatus: string;
  distance: number;
  amount: number;
  deliveryFee: number;
  commissionRate: number;
  executiveEarning: number;
  priority: string;
  isSurgePricing: boolean;
  surgeMultiplier: number;
  estimatedDeliveryTime: number;
  paymentMethod: string;
  optimalScore: number;
  pickupLocation: { x: number; y: number };
  deliveryLocation: { x: number; y: number };
}

export interface ExecutiveProfile {
  id: number;
  name: string;
  mobile: string;
  email: string;
  licenseNo: string;
  vehicleType: string;
  vehicleNumber: string;
  aadharNo: string;
  averageRating: number;
  totalDeliveries: number;
  totalEarnings: number;
}

export interface WalletInfo {
  balance: number;
  totalEarnings: number;
  pendingAmount: number;
  transactions: WalletTransaction[];
}

export interface WalletTransaction {
  id: number;
  amount: number;
  type: string;
  status: string;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class DeliveryExecutiveService {
  private apiUrl = `${environment.apiUrl}/delivery-executive`;

  constructor(private http: HttpClient) {}

  getNearbyOrders(
    executiveId: number,
    latitude: number,
    longitude: number,
    sortBy: string = 'optimal'
  ): Observable<OrderSummary[]> {
    const params = new HttpParams()
      .set('latitude', latitude.toString())
      .set('longitude', longitude.toString())
      .set('sortBy', sortBy);

    return this.http.get<OrderSummary[]>(`${this.apiUrl}/${executiveId}/nearby-orders`, { params });
  }

  acceptOrder(executiveId: number, orderId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${executiveId}/orders/${orderId}/accept`, {});
  }

  confirmPickup(executiveId: number, orderId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${executiveId}/orders/${orderId}/pickup`, {});
  }

  confirmDelivery(executiveId: number, orderId: number, otp: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/${executiveId}/orders/${orderId}/deliver`, { customerOtp: otp });
  }

  getActiveOrders(executiveId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${executiveId}/orders/active`);
  }

  getDeliveredOrders(executiveId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${executiveId}/orders/delivered`);
  }

  getWallet(executiveId: number): Observable<WalletInfo> {
    return this.http.get<WalletInfo>(`${this.apiUrl}/${executiveId}/wallet`);
  }

  withdrawFromWallet(executiveId: number, amount: number): Observable<WalletInfo> {
    const params = new HttpParams()
      .set('role', 'DELIVERYEXECUTIVE')
      .set('amount', amount.toString());

    return this.http.post<WalletInfo>(`${environment.apiUrl}/wallet/${executiveId}/deduct`, null, { params });
  }

  getRatings(executiveId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${executiveId}/ratings`);
  }

  getAverageRating(executiveId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${executiveId}/ratings/average`);
  }

  updateStatus(executiveId: number, isOnline: boolean): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${executiveId}/status`, { online: isOnline });
  }

  updateLocation(executiveId: number, latitude: number, longitude: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${executiveId}/location`, { x: latitude, y: longitude });
  }

  getProfile(executiveId: number): Observable<ExecutiveProfile> {
    return this.http.get<ExecutiveProfile>(`${this.apiUrl}/${executiveId}/profile`);
  }

  updateProfile(executiveId: number, profile: Partial<ExecutiveProfile>): Observable<ExecutiveProfile> {
    return this.http.put<ExecutiveProfile>(`${this.apiUrl}/${executiveId}/profile`, profile);
  }

  // Bank Account methods
  getBankAccounts(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/bank-accounts`);
  }

  addBankAccount(bankData: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/bank-accounts`, bankData);
  }

  deleteBankAccount(accountId: number): Observable<any> {
    return this.http.delete(`${environment.apiUrl}/bank-accounts/${accountId}`);
  }

  setPrimaryBankAccount(accountId: number): Observable<any> {
    return this.http.put(`${environment.apiUrl}/bank-accounts/${accountId}/primary`, {});
  }
}
