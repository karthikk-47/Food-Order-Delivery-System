import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../../environments/environment';

export interface CartItem {
  id: number;
  menuItemId: number;
  itemName: string;
  description: string;
  imageUrl: string;
  price: number;
  quantity: number;
  subtotal: number;
  homemakerId: number;
  homemakerName: string;
  specialInstructions: string;
  isVegetarian: boolean;
}

export interface Cart {
  id: number;
  userId: number;
  items: CartItem[];
  totalAmount: number;
  itemCount: number;
  deliveryFee: number;
  grandTotal: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = `${environment.apiUrl}/cart`;
  private cartSubject = new BehaviorSubject<Cart | null>(null);
  
  cart$ = this.cartSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadCart();
  }

  private loadCart(): void {
    this.getCart().subscribe({
      next: (cart) => this.cartSubject.next(cart),
      error: () => this.cartSubject.next(null)
    });
  }

  getCart(): Observable<Cart> {
    return this.http.get<Cart>(this.apiUrl);
  }


  addToCart(menuItemId: number, homemakerId: number, quantity: number = 1, specialInstructions?: string): Observable<Cart> {
    return this.http.post<Cart>(`${this.apiUrl}/items`, {
      menuItemId,
      homemakerId,
      quantity,
      specialInstructions
    }).pipe(tap(cart => this.cartSubject.next(cart)));
  }

  updateCartItem(itemId: number, quantity: number, specialInstructions?: string): Observable<Cart> {
    return this.http.put<Cart>(`${this.apiUrl}/items/${itemId}`, {
      quantity,
      specialInstructions
    }).pipe(tap(cart => this.cartSubject.next(cart)));
  }

  removeFromCart(itemId: number): Observable<Cart> {
    return this.http.delete<Cart>(`${this.apiUrl}/items/${itemId}`)
      .pipe(tap(cart => this.cartSubject.next(cart)));
  }

  clearCart(): Observable<void> {
    return this.http.delete<void>(this.apiUrl)
      .pipe(tap(() => this.cartSubject.next(null)));
  }

  refreshCart(): void {
    this.loadCart();
  }

  getItemCount(): number {
    return this.cartSubject.value?.itemCount || 0;
  }
}
