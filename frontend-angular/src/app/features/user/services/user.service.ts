import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { AuthService } from '../../../core/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = environment.apiUrl;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  // Menu & Homemaker APIs
  getAllHomemakers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers`);
  }

  getNearbyHomemakers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/nearby`);
  }

  getHomemakerById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${id}`);
  }

  getHomemakerMenus(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}/menus`);
  }

  getHomemakerMenu(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}/menu`);
  }

  getMenuItems(menuId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/menus/${menuId}/items`);
  }

  // Stats APIs
  getActiveOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/orders/active`);
  }

  getOrderStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/orders/stats`);
  }

  // Order APIs
  createOrder(orderData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/orders`, orderData);
  }

  getUserOrders(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/orders/user/${userId}`);
  }

  getOrderById(orderId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/orders/${orderId}`);
  }

  cancelOrder(orderId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/orders/${orderId}/cancel`, {});
  }

  trackOrder(orderId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/orders/${orderId}/track`);
  }

  // Payment APIs
  createPayment(paymentData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/order-payments/create`, paymentData);
  }

  verifyPayment(verificationData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/order-payments/verify`, verificationData);
  }

  getPaymentHistory(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/order-payments/user/${userId}`);
  }

  getPaymentStats(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/order-payments/user/${userId}/stats`);
  }

  // Address APIs
  getUserAddresses(userId?: number): Observable<any> {
    const id = userId || this.authService.getUserId();
    return this.http.get(`${this.apiUrl}/users/${id}/addresses`);
  }

  addAddress(address: any, userId?: number): Observable<any> {
    const id = userId || this.authService.getUserId();
    return this.http.post(`${this.apiUrl}/users/${id}/addresses`, address);
  }

  updateAddress(addressId: number, address: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/addresses/${addressId}`, address);
  }

  deleteAddress(addressId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/addresses/${addressId}`);
  }

  // Map / Reverse Geocode
  reverseGeocode(lat: number, lng: number): Observable<any> {
    const location = `${lat},${lng}`;
    // send as JSON object so backend can parse robustly
    return this.http.post(`${this.apiUrl}/getAddress`, { location });
  }

  // Profile APIs
  getUserProfile(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/users/${userId}`);
  }

  updateUserProfile(userId: number, profile: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/users/${userId}`, profile);
  }

  // Rating APIs
  rateOrder(ratingData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/ratings`, ratingData);
  }

  getUserRatings(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/ratings/user/${userId}`);
  }
}