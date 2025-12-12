import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CartService, Cart, CartItem } from '../services/cart.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit, OnDestroy {
  cart: Cart | null = null;
  loading = false;
  error = '';
  private subscription?: Subscription;

  constructor(
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCart();
    this.subscription = this.cartService.cart$.subscribe(cart => {
      this.cart = cart;
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  loadCart(): void {
    this.loading = true;
    this.cartService.getCart().subscribe({
      next: (cart) => {
        this.cart = cart;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load cart';
        this.loading = false;
      }
    });
  }

  updateQuantity(item: CartItem, change: number): void {
    const newQuantity = item.quantity + change;
    if (newQuantity < 1) {
      this.removeItem(item);
      return;
    }
    this.cartService.updateCartItem(item.id, newQuantity).subscribe();
  }


  removeItem(item: CartItem): void {
    this.cartService.removeFromCart(item.id).subscribe();
  }

  clearCart(): void {
    if (confirm('Are you sure you want to clear your cart?')) {
      this.cartService.clearCart().subscribe(() => {
        this.cart = null;
      });
    }
  }

  proceedToCheckout(): void {
    if (this.cart && this.cart.items.length > 0) {
      this.router.navigate(['/user/checkout']);
    }
  }

  continueShopping(): void {
    this.router.navigate(['/user/browse']);
  }
}
