import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  // Home Page (Landing)
  {
    path: '',
    loadComponent: () => import('./features/home/home.component').then(m => m.HomeComponent)
  },

  // Role-specific Login Routes
  {
    path: 'login',
    children: [
      {
        path: '',
        redirectTo: '/login/user',
        pathMatch: 'full'
      },
      {
        path: 'user',
        loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent),
        data: { role: 'USER', title: 'Customer Login', color: '#071627' }
      },
      {
        path: 'homemaker',
        loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent),
        data: { role: 'HOMEMAKER', title: 'Home Chef Login', color: '#FF8A00' }
      },
      {
        path: 'executive',
        loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent),
        data: { role: 'DELIVERYEXECUTIVE', title: 'Delivery Partner Login', color: '#00c853' }
      },
      {
        path: 'admin',
        loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent),
        data: { role: 'ADMIN', title: 'Admin Login', color: '#9B59B6' }
      }
    ]
  },

  // Role-specific Signup Routes
  {
    path: 'signup',
    children: [
      {
        path: '',
        redirectTo: '/signup/user',
        pathMatch: 'full'
      },
      {
        path: 'user',
        loadComponent: () => import('./features/auth/signup/signup.component').then(m => m.SignupComponent),
        data: { role: 'USER', title: 'Customer Signup', color: '#071627' }
      },
      {
        path: 'homemaker',
        loadComponent: () => import('./features/auth/signup/signup.component').then(m => m.SignupComponent),
        data: { role: 'HOMEMAKER', title: 'Home Chef Signup', color: '#FF8A00' }
      },
      {
        path: 'executive',
        loadComponent: () => import('./features/auth/signup/signup.component').then(m => m.SignupComponent),
        data: { role: 'DELIVERYEXECUTIVE', title: 'Delivery Partner Signup', color: '#00c853' }
      }
    ]
  },

  // Dev Login (for testing dashboards without authentication)
  {
    path: 'dev-login',
    loadComponent: () => import('./features/auth/dev-login/dev-login.component').then(m => m.DevLoginComponent)
  },

  // Delivery Executive Dashboard
  {
    path: 'delivery-executive',
    canActivate: [AuthGuard],
    data: { roles: ['DELIVERYEXECUTIVE'] },
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/delivery-executive/dashboard/dashboard.component').then(m => m.DashboardComponent)
      },
      {
        path: 'orders',
        loadComponent: () => import('./features/delivery-executive/orders/orders.component').then(m => m.OrdersComponent)
      },
      {
        path: 'wallet',
        loadComponent: () => import('./features/delivery-executive/wallet/wallet.component').then(m => m.WalletComponent)
      },
      {
        path: 'profile',
        loadComponent: () => import('./features/delivery-executive/profile/profile.component').then(m => m.ProfileComponent)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },

  // Homemaker Dashboard
  {
    path: 'homemaker',
    canActivate: [AuthGuard],
    data: { roles: ['HOMEMAKER'] },
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/homemaker/dashboard/homemaker-dashboard.component').then(m => m.HomemakerDashboardComponent)
      },
      {
        path: 'menu',
        loadComponent: () => import('./features/homemaker/menu/menu-management.component').then(m => m.MenuManagementComponent)
      },
      {
        path: 'wallet',
        loadComponent: () => import('./features/homemaker/wallet/wallet.component').then(m => m.WalletComponent)
      },
      {
        path: 'orders',
        loadComponent: () => import('./features/delivery-executive/orders/orders.component').then(m => m.OrdersComponent)
      },
      {
        path: 'profile',
        loadComponent: () => import('./features/homemaker/profile/homemaker-profile.component').then(m => m.HomemakerProfileComponent)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },

  // User Dashboard
  {
    path: 'user',
    canActivate: [AuthGuard],
    data: { roles: ['USER'] },
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/user/dashboard/user-dashboard.component').then(m => m.UserDashboardComponent)
      },
      {
        path: 'browse',
        loadComponent: () => import('./features/user/browse/browse.component').then(m => m.BrowseComponent)
      },
      {
        path: 'homemaker/:id',
        loadComponent: () => import('./features/user/order/place-order.component').then(m => m.PlaceOrderComponent)
      },
      {
        path: 'cart',
        loadComponent: () => import('./features/user/cart/cart.component').then(m => m.CartComponent)
      },
      {
        path: 'track/:orderId',
        loadComponent: () => import('./features/user/order-tracking/order-tracking.component').then(m => m.OrderTrackingComponent)
      },
      {
        path: 'orders',
        loadComponent: () => import('./features/delivery-executive/orders/orders.component').then(m => m.OrdersComponent)
      },
      {
        path: 'profile',
        loadComponent: () => import('./features/user/profile/user-profile.component').then(m => m.UserProfileComponent)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },

  // Admin Dashboard
  {
    path: 'admin',
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN'] },
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/admin/dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent)
      },
      {
        path: 'users',
        loadComponent: () => import('./features/admin/dashboard/user-management.component').then(m => m.UserManagementComponent)
      },
      {
        path: 'homemakers',
        loadComponent: () => import('./features/admin/dashboard/homemaker-management.component').then(m => m.HomemakerManagementComponent)
      },
      {
        path: 'executives',
        loadComponent: () => import('./features/admin/dashboard/executive-management.component').then(m => m.ExecutiveManagementComponent)
      },
      {
        path: 'manage',
        children: [
          {
            path: 'users',
            loadComponent: () => import('./features/admin/dashboard/user-management.component').then(m => m.UserManagementComponent)
          },
          {
            path: 'homemakers',
            loadComponent: () => import('./features/admin/dashboard/homemaker-management.component').then(m => m.HomemakerManagementComponent)
          },
          {
            path: 'executives',
            loadComponent: () => import('./features/admin/dashboard/executive-management.component').then(m => m.ExecutiveManagementComponent)
          },
          {
            path: '',
            redirectTo: 'users',
            pathMatch: 'full'
          }
        ]
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },

  // Unauthorized page
  {
    path: 'unauthorized',
    loadComponent: () => import('./shared/components/unauthorized/unauthorized.component').then(m => m.UnauthorizedComponent)
  },

  // Fallback
  {
    path: '**',
    redirectTo: ''
  }
];
