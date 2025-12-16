import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

export interface BankDetails {
  accountHolderName: string;
  accountNumber: string;
  ifscCode: string;
  bankName: string;
  branchName?: string;
  accountType: 'SAVINGS' | 'CURRENT';
}

export interface RegisterRequest {
  // Basic Info
  name: string;
  email: string;
  mobile: string;
  password: string;
  role: 'USER' | 'HOMEMAKER' | 'DELIVERYEXECUTIVE';
  
  // Common Fields
  address?: string;
  
  // HOMEMAKER specific
  cuisineTypes?: string[];
  fssaiLicense?: string;
  kitchenDescription?: string;
  
  // DELIVERYEXECUTIVE specific
  licenseNo?: string;
  vehicleType?: string;
  vehicleNumber?: string;
  aadharNo?: string;
  
  // Payment Details (for HOMEMAKER and DELIVERYEXECUTIVE)
  bankDetails?: BankDetails;
  
  // Additional verification
  panNumber?: string;  // For tax purposes
  gstNumber?: string;  // If applicable
}

export interface LoginRequest {
  mobile: string;
  password: string;
}

export interface AuthResponse {
  accessToken: string;
  tokenType: string;
  userId: number;
  mobile: string;
  role: 'DELIVERYEXECUTIVE' | 'HOMEMAKER' | 'USER' | 'ADMIN';
}

export interface User {
  id: number;
  mobile: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    const storedUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<User | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  public get token(): string | null {
    return localStorage.getItem('token');
  }

  login(mobile: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/login`, { mobile, password })
      .pipe(
        tap(response => {
          if (response && response.accessToken) {
            this.setAuthData(response);
          }
        })
      );
  }

  register(userData: RegisterRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/register`, userData)
      .pipe(
        tap(response => {
          // Only set auth data if we got a token (USER registration)
          if (response && response.accessToken) {
            this.setAuthData(response);
          }
        })
      );
  }

  // Dev login for testing without backend authentication
  devLogin(role: 'USER' | 'HOMEMAKER' | 'DELIVERYEXECUTIVE' | 'ADMIN'): void {
    const mockResponse: AuthResponse = {
      accessToken: 'dev-token-' + Date.now(),
      tokenType: 'Bearer',
      userId: Math.floor(Math.random() * 1000),
      mobile: '9999999999',
      role: role
    };
    this.setAuthData(mockResponse);
    this.navigateByRole(role);
  }

  private setAuthData(response: AuthResponse): void {
    localStorage.setItem('token', response.accessToken);
    const user: User = {
      id: response.userId,
      mobile: response.mobile,
      role: response.role
    };
    localStorage.setItem('currentUser', JSON.stringify(user));
    localStorage.setItem('userId', response.userId.toString());
    localStorage.setItem('userRole', response.role);
    this.currentUserSubject.next(user);
  }

  private navigateByRole(role: string): void {
    switch (role) {
      case 'USER':
        this.router.navigate(['/user/dashboard']);
        break;
      case 'HOMEMAKER':
        this.router.navigate(['/homemaker/dashboard']);
        break;
      case 'DELIVERYEXECUTIVE':
        this.router.navigate(['/delivery-executive/dashboard']);
        break;
      case 'ADMIN':
        this.router.navigate(['/admin/dashboard']);
        break;
      default:
        this.router.navigate(['/']);
    }
  }

  logout(redirect: boolean = true): void {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    localStorage.removeItem('userId');
    localStorage.removeItem('userRole');
    this.currentUserSubject.next(null);
    if (redirect) {
      this.router.navigate(['/login']);
    }
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  hasRole(role: string): boolean {
    const user = this.currentUserValue;
    return user ? user.role === role : false;
  }

  getUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? parseInt(userId) : null;
  }

  getUserRole(): string | null {
    return localStorage.getItem('userRole');
  }

  validateToken(): Observable<any> {
    return this.http.get(`${this.apiUrl}/auth/validate`);
  }

  getAuthHeaders(): HttpHeaders {
    const token = this.token;
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });
  }

  // Forgot Password Methods
  forgotPassword(mobile: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/forgot-password`, { mobile });
  }

  verifyOTP(mobile: string, otp: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/verify-otp`, { mobile, otp });
  }

  resetPassword(mobile: string, otp: string, newPassword: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/reset-password`, { mobile, otp, newPassword });
  }
}
