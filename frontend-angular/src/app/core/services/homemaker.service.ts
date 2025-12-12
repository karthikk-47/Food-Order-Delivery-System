import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface HomemakerProfile {
  id: number;
  name: string;
  mobile: string;
  email: string;
  businessName?: string;
  address: string;
  city: string;
  state: string;
  pincode: string;
  fssaiLicense?: string;
  averageRating: number;
  totalOrders: number;
  totalEarnings: number;
  isVerified: boolean;
  profilePicture?: string;
}

export interface Menu {
  id: number;
  homemakerId: number;
  status: string;
  publishedDate?: string;
  validFrom?: string;
  validUntil?: string;
  isRecurring: boolean;
  recurrencePattern?: string;
  cuisineTypes: string[];
  description: string;
  estimatedPrepTime: number;
  menuType: string;
  minOrderQuantity: number;
  maxOrderQuantity: number;
  averageRating: number;
  totalOrders: number;
  items: MenuItem[];
}

export interface MenuItem {
  id: number;
  menuId: number;
  name: string;
  description: string;
  price: number;
  category: string;
  isVegetarian: boolean;
  isVegan: boolean;
  spiceLevel: string;
  allergens: string[];
  calories?: number;
  servingSize: string;
  imageUrl?: string;
  isAvailable: boolean;
  preparationTime: number;
  rating: number;
  orderCount: number;
}

export interface HomemakerAnalytics {
  id: number;
  homemakerId: number;
  totalRevenue: number;
  totalOrders: number;
  averageOrderValue: number;
  completionRate: number;
  cancellationRate: number;
  averageRating: number;
  totalReviews: number;
  popularItems: string[];
  peakOrderHours: number[];
  repeatCustomerRate: number;
}

export interface HomemakerOrder {
  id: number;
  userId: number;
  userName: string;
  orderStatus: string;
  totalAmount: number;
  deliveryFee: number;
  orderDate: string;
  deliveryAddress: string;
  items: OrderItem[];
  paymentMethod: string;
  paymentStatus: string;
  estimatedDeliveryTime?: string;
}

export interface OrderItem {
  id: number;
  menuItemId: number;
  menuItemName: string;
  quantity: number;
  price: number;
  subtotal: number;
}

@Injectable({
  providedIn: 'root'
})
export class HomemakerService {
  private apiUrl = `${environment.apiUrl}/homemaker`;

  constructor(private http: HttpClient) {}

  // Profile Management
  getProfile(homemakerId: number): Observable<HomemakerProfile> {
    return this.http.get<HomemakerProfile>(`${this.apiUrl}/profile/${homemakerId}`);
  }

  updateProfile(homemakerId: number, profile: Partial<HomemakerProfile>): Observable<HomemakerProfile> {
    return this.http.put<HomemakerProfile>(`${this.apiUrl}/profile/${homemakerId}`, profile);
  }

  // Menu Management
  getMenus(homemakerId: number, status?: string): Observable<Menu[]> {
    let params = new HttpParams();
    if (status) {
      params = params.set('status', status);
    }
    return this.http.get<Menu[]>(`${this.apiUrl}/menus/${homemakerId}`, { params });
  }

  getMenuById(menuId: number): Observable<Menu> {
    return this.http.get<Menu>(`${this.apiUrl}/menus/details/${menuId}`);
  }

  createMenu(homemakerId: number, menu: Partial<Menu>): Observable<Menu> {
    return this.http.post<Menu>(`${this.apiUrl}/menus/${homemakerId}`, menu);
  }

  updateMenu(menuId: number, menu: Partial<Menu>): Observable<Menu> {
    return this.http.put<Menu>(`${this.apiUrl}/menus/${menuId}`, menu);
  }

  deleteMenu(menuId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/menus/${menuId}`);
  }

  publishMenu(menuId: number): Observable<Menu> {
    return this.http.put<Menu>(`${this.apiUrl}/menus/${menuId}/publish`, {});
  }

  // Menu Item Management
  getMenuItems(menuId: number): Observable<MenuItem[]> {
    return this.http.get<MenuItem[]>(`${this.apiUrl}/menu-items/${menuId}`);
  }

  createMenuItem(menuId: number, item: Partial<MenuItem>): Observable<MenuItem> {
    return this.http.post<MenuItem>(`${this.apiUrl}/menu-items/${menuId}`, item);
  }

  updateMenuItem(itemId: number, item: Partial<MenuItem>): Observable<MenuItem> {
    return this.http.put<MenuItem>(`${this.apiUrl}/menu-items/${itemId}`, item);
  }

  deleteMenuItem(itemId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/menu-items/${itemId}`);
  }

  toggleItemAvailability(itemId: number): Observable<MenuItem> {
    return this.http.put<MenuItem>(`${this.apiUrl}/menu-items/${itemId}/toggle-availability`, {});
  }

  // Order Management
  getOrders(homemakerId: number, status?: string): Observable<HomemakerOrder[]> {
    let params = new HttpParams();
    if (status) {
      params = params.set('status', status);
    }
    return this.http.get<HomemakerOrder[]>(`${this.apiUrl}/orders/${homemakerId}`, { params });
  }

  acceptOrder(orderId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/orders/${orderId}/accept`, {});
  }

  rejectOrder(orderId: number, reason: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/orders/${orderId}/reject`, { reason });
  }

  markOrderReady(orderId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/orders/${orderId}/ready`, {});
  }

  // Analytics
  getAnalytics(homemakerId: number): Observable<HomemakerAnalytics> {
    return this.http.get<HomemakerAnalytics>(`${this.apiUrl}/analytics/${homemakerId}`);
  }

  getRevenueByPeriod(homemakerId: number, period: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/analytics/${homemakerId}/revenue`, {
      params: { period }
    });
  }
}