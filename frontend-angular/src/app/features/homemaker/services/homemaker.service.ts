import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomemakerService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getDashboardStats(): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/stats`);
  }

  getRecentOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/orders/homemaker/recent`);
  }

  getTopMenuItems(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/menu-items/top`);
  }

  getMenus(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/menu/homemaker/${homemakerId}/all`);
  }

  getActiveMenus(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/menu/homemaker/${homemakerId}/active`);
  }

  createMenu(homemakerId: number, menuData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/menu/create`, menuData);
  }

  updateMenu(menuId: number, menuData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/menus/${menuId}`, menuData);
  }

  deleteMenu(menuId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/menus/${menuId}`);
  }

  getMenuItems(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/menu-items`);
  }

  addMenuItem(itemData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/menu-items`, itemData);
  }

  addMenuItemWithPhoto(formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/menu-item/create`, formData);
  }

  updateMenuItem(itemId: number, itemData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/menu-items/${itemId}`, itemData);
  }

  updateMenuItemWithPhoto(itemId: number, formData: FormData): Observable<any> {
    return this.http.put(`${this.apiUrl}/menu-item/${itemId}`, formData);
  }

  deleteMenuItem(itemId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/menu-items/${itemId}`);
  }

  getHomemakerOrders(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/orders/homemaker/${homemakerId}`);
  }

  acceptOrder(orderId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/orders/${orderId}/accept`, {});
  }

  markOrderReady(orderId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/orders/${orderId}/ready`, {});
  }

  getHomemakerStats(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}/stats`);
  }

  getRevenueAnalytics(homemakerId: number, period: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}/analytics/revenue?period=${period}`);
  }

  getHomemakerProfile(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}`);
  }

  updateHomemakerProfile(homemakerId: number, profileData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/homemakers/${homemakerId}`, profileData);
  }

  // Alias methods for profile component
  getProfile(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers/${homemakerId}`);
  }

  updateProfile(homemakerId: number, profileData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/homemakers/${homemakerId}`, profileData);
  }

  // Wallet methods
  getWallet(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemaker/wallet/${homemakerId}`);
  }

  requestWithdrawal(homemakerId: number, withdrawalData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/homemaker/wallet/${homemakerId}/withdraw`, withdrawalData);
  }

  getWithdrawalHistory(homemakerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemaker/wallet/${homemakerId}/withdrawals`);
  }

  getWithdrawal(withdrawalId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemaker/wallet/withdrawal/${withdrawalId}`);
  }

  getPendingWithdrawals(): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemaker/wallet/admin/pending`);
  }

  approveWithdrawal(withdrawalId: number, transactionId: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/homemaker/wallet/admin/withdrawal/${withdrawalId}/approve?transactionId=${transactionId}`, {});
  }

  completeWithdrawal(withdrawalId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/homemaker/wallet/admin/withdrawal/${withdrawalId}/complete`, {});
  }

  rejectWithdrawal(withdrawalId: number, reason: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/homemaker/wallet/admin/withdrawal/${withdrawalId}/reject?reason=${reason}`, {});
  }

  // Map / Reverse Geocode - uses OpenStreetMap Nominatim for global coverage
  reverseGeocode(lat: number, lng: number): Observable<any> {
    const url = `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}&addressdetails=1`;
    return this.http.get(url, {
      headers: { 'Accept': 'application/json' }
    });
  }

  // Bank Account methods
  getBankAccounts(homemakerId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/homemaker/wallet/${homemakerId}/bank-accounts`);
  }

  addBankAccount(homemakerId: number, bankData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/homemaker/wallet/${homemakerId}/bank-accounts`, bankData);
  }

  deleteBankAccount(homemakerId: number, accountId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/homemaker/wallet/${homemakerId}/bank-accounts/${accountId}`);
  }
}