import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

declare var Razorpay: any;

@Component({
  selector: 'app-place-order',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.scss']
})
export class PlaceOrderComponent implements OnInit {
  homemakerId!: number;
  menuItems: any[] = [];
  cart: any[] = [];
  selectedAddress: any = null;
  addresses: any[] = [];
  total = 0;
  deliveryFee = 40;
  loading = false;
  userId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.userId = this.authService.getUserId();
    if (!this.userId) {
      alert('Please login to place an order');
      this.router.navigate(['/login']);
      return;
    }
    this.homemakerId = +this.route.snapshot.params['id'];
    this.loadMenuItems();
    this.loadAddresses();
  }

  loadMenuItems() {
    this.userService.getHomemakerMenu(this.homemakerId).subscribe({
      next: (items) => {
        this.menuItems = items;
      },
      error: (err) => console.error('Error loading menu:', err)
    });
  }

  loadAddresses() {
    this.userService.getUserAddresses().subscribe({
      next: (addresses: any) => {
        this.addresses = addresses;
        if (addresses.length > 0) {
          this.selectedAddress = addresses.find((a: any) => a.isDefault) || addresses[0];
        }
      },
      error: (err: any) => console.error('Error loading addresses:', err)
    });
  }

  addToCart(item: any) {
    const existing = this.cart.find(c => c.id === item.id);
    if (existing) {
      existing.quantity++;
    } else {
      this.cart.push({ ...item, quantity: 1 });
    }
    this.calculateTotal();
  }

  removeFromCart(item: any) {
    const index = this.cart.findIndex(c => c.id === item.id);
    if (index > -1) {
      if (this.cart[index].quantity > 1) {
        this.cart[index].quantity--;
      } else {
        this.cart.splice(index, 1);
      }
    }
    this.calculateTotal();
  }

  calculateTotal() {
    this.total = this.cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  }

  placeOrder() {
    if (!this.userId) {
      alert('Please login to place an order');
      this.router.navigate(['/login']);
      return;
    }

    if (!this.selectedAddress) {
      alert('Please select a delivery address');
      return;
    }

    if (this.cart.length === 0) {
      alert('Please add items to cart');
      return;
    }

    this.loading = true;
    const orderData = {
      homemakerId: this.homemakerId,
      userId: this.userId,
      items: this.cart,
      addressId: this.selectedAddress.id,
      amount: this.total,
      deliveryFee: this.deliveryFee
    };

    this.userService.createOrder(orderData).subscribe({
      next: (order) => {
        this.initiatePayment(order);
      },
      error: (err) => {
        console.error('Error creating order:', err);
        alert('Failed to create order. Please try again.');
        this.loading = false;
      }
    });
  }

  initiatePayment(order: any) {
    const paymentData = {
      orderId: order.id,
      userId: this.userId!,
      amount: this.total + this.deliveryFee,
      customerEmail: order.customerEmail || 'user@example.com',
      customerPhone: order.customerPhone || this.authService.currentUserValue?.mobile
    };

    this.userService.createPayment(paymentData).subscribe({
      next: (payment) => {
        this.openRazorpay(payment);
      },
      error: (err) => {
        console.error('Error creating payment:', err);
        this.loading = false;
      }
    });
  }

  openRazorpay(payment: any) {
    const options = {
      key: payment.razorpayKey,
      amount: payment.amount * 100,
      currency: payment.currency,
      name: 'Food Delivery App',
      description: `Order #${payment.orderId}`,
      order_id: payment.razorpayOrderId,
      handler: (response: any) => {
        this.verifyPayment(response);
      },
      prefill: {
        email: payment.customerEmail,
        contact: payment.customerPhone
      },
      theme: {
        color: '#667eea'
      }
    };

    const rzp = new Razorpay(options);
    rzp.on('payment.failed', (response: any) => {
      this.handlePaymentFailure(response);
    });
    rzp.open();
    this.loading = false;
  }

  verifyPayment(response: any) {
    const verifyData = {
      razorpayOrderId: response.razorpay_order_id,
      razorpayPaymentId: response.razorpay_payment_id,
      razorpaySignature: response.razorpay_signature
    };

    this.userService.verifyPayment(verifyData).subscribe({
      next: () => {
        alert('Payment successful! Your order has been placed.');
        this.router.navigate(['/user/orders']);
      },
      error: (err) => {
        console.error('Payment verification failed:', err);
        alert('Payment verification failed. Please contact support.');
      }
    });
  }

  handlePaymentFailure(response: any) {
    console.error('Payment failed:', response);
    alert('Payment failed. Please try again.');
  }
}
