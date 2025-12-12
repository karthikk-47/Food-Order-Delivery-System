import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

interface RoleConfig {
  role: string;
  title: string;
  color: string;
  icon: string;
  gradient: string;
  dashboardRoute: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  error = '';
  showPassword = false;

  // Role configuration based on route
  roleConfig: RoleConfig = {
    role: 'USER',
    title: 'Customer Login',
    color: '#071627',
    icon: '🍽️',
    gradient: 'linear-gradient(135deg, #071627 0%, #1c3d5a 100%)',
    dashboardRoute: '/user/dashboard'
  };

  private roleConfigs: { [key: string]: RoleConfig } = {
    'USER': {
      role: 'USER',
      title: 'Customer Login',
      color: '#071627',
      icon: '🍽️',
      gradient: 'linear-gradient(135deg, #071627 0%, #1c3d5a 100%)',
      dashboardRoute: '/user/dashboard'
    },
    'HOMEMAKER': {
      role: 'HOMEMAKER',
      title: 'Home Chef Login',
      color: '#FF8A00',
      icon: '👨‍🍳',
      gradient: 'linear-gradient(135deg, #FF8A00 0%, #E67E00 100%)',
      dashboardRoute: '/homemaker/dashboard'
    },
    'DELIVERYEXECUTIVE': {
      role: 'DELIVERYEXECUTIVE',
      title: 'Delivery Partner Login',
      color: '#00c853',
      icon: '🚴',
      gradient: 'linear-gradient(135deg, #00c853 0%, #009624 100%)',
      dashboardRoute: '/delivery-executive/dashboard'
    },
    'ADMIN': {
      role: 'ADMIN',
      title: 'Admin Login',
      color: '#9B59B6',
      icon: '⚙️',
      gradient: 'linear-gradient(135deg, #9B59B6 0%, #8E44AD 100%)',
      dashboardRoute: '/admin/dashboard'
    }
  };

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      mobile: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

    // Get role from route data and handle auth redirection
    this.route.data.subscribe(data => {
      if (data['role'] && this.roleConfigs[data['role']]) {
        this.roleConfig = this.roleConfigs[data['role']];
      }

      // If already logged in
      if (this.authService.isAuthenticated()) {
        const user = this.authService.currentUserValue;
        if (user) {
          // If the logged-in user's role matches the current login page role
          if (user.role === this.roleConfig.role) {
            this.redirectToDashboard(user.role);
          } else {
            // If roles don't match (e.g. logged in as Executive but visiting User login),
            // logout the current user without redirecting so they can login as the new role
            this.authService.logout(false);
          }
        }
      }
    });
  }

  get f() { return this.loginForm.controls; }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.error = '';

    const { mobile, password } = this.loginForm.value;

    this.authService.login(mobile, password).subscribe({
      next: (response) => {
        this.loading = false;
        // Check if user role matches the login page role (unless USER which is generic)
        if (this.roleConfig.role !== 'USER' && response.role !== this.roleConfig.role) {
          this.error = `This account is not registered as a ${this.getRoleDisplayName(this.roleConfig.role)}. Please use the correct login portal.`;
          this.authService.logout();
          return;
        }
        this.redirectToDashboard(response.role);
      },
      error: (err) => {
        this.loading = false;
        this.error = err.error?.message || 'Invalid credentials. Please try again.';
      }
    });
  }

  private redirectToDashboard(role: string): void {
    const config = this.roleConfigs[role];
    if (config) {
      this.router.navigate([config.dashboardRoute]);
    } else {
      this.router.navigate(['/']);
    }
  }

  private getRoleDisplayName(role: string): string {
    const names: { [key: string]: string } = {
      'USER': 'Customer',
      'HOMEMAKER': 'Home Chef',
      'DELIVERYEXECUTIVE': 'Delivery Partner',
      'ADMIN': 'Administrator'
    };
    return names[role] || role;
  }

  getSignupRoute(): string {
    const routes: { [key: string]: string } = {
      'USER': '/signup/user',
      'HOMEMAKER': '/signup/homemaker',
      'DELIVERYEXECUTIVE': '/signup/executive',
      'ADMIN': '' // No signup for admin
    };
    return routes[this.roleConfig.role] || '/signup/user';
  }
}
