import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

interface TestUser {
    role: string;
    name: string;
    mobile: string;
    icon: string;
    color: string;
    dashboard: string;
}

@Component({
    selector: 'app-dev-login',
    standalone: true,
    imports: [CommonModule, RouterModule],
    template: `
    <div class="dev-login">
      <div class="dev-container">
        <div class="dev-header">
          <h1>🔧 Developer Login</h1>
          <p>Quick access to test different role dashboards</p>
          <span class="dev-badge">DEV ONLY</span>
        </div>
        
        <div class="role-cards">
          <div 
            *ngFor="let user of testUsers" 
            class="role-card"
            [style.--role-color]="user.color"
            (click)="quickLogin(user)"
          >
            <div class="role-icon">{{ user.icon }}</div>
            <div class="role-info">
              <h3>{{ user.role }}</h3>
              <p>{{ user.name }}</p>
              <span class="mobile">{{ user.mobile }}</span>
            </div>
            <div class="arrow">→</div>
          </div>
        </div>
        
        <div class="dev-footer">
          <a routerLink="/" class="back-link">← Back to Home</a>
          <p class="warning">⚠️ This page is for development testing only</p>
        </div>
      </div>
    </div>
  `,
    styles: [`
    .dev-login {
      min-height: 100vh;
      background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 20px;
    }
    
    .dev-container {
      max-width: 500px;
      width: 100%;
    }
    
    .dev-header {
      text-align: center;
      color: white;
      margin-bottom: 32px;
      
      h1 { font-size: 2rem; margin-bottom: 8px; }
      p { opacity: 0.7; margin-bottom: 16px; }
    }
    
    .dev-badge {
      display: inline-block;
      background: #00c853;
      color: white;
      padding: 4px 12px;
      border-radius: 20px;
      font-size: 12px;
      font-weight: 600;
    }
    
    .role-cards {
      display: flex;
      flex-direction: column;
      gap: 12px;
    }
    
    .role-card {
      display: flex;
      align-items: center;
      gap: 16px;
      padding: 20px;
      background: rgba(255, 255, 255, 0.05);
      border: 1px solid rgba(255, 255, 255, 0.1);
      border-radius: 12px;
      cursor: pointer;
      transition: all 0.3s ease;
      
      &:hover {
        background: rgba(255, 255, 255, 0.1);
        transform: translateX(8px);
        border-color: var(--role-color);
      }
      
      .role-icon {
        font-size: 2.5rem;
        width: 60px;
        height: 60px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: rgba(255, 255, 255, 0.1);
        border-radius: 12px;
      }
      
      .role-info {
        flex: 1;
        color: white;
        
        h3 { 
          font-size: 1.1rem; 
          margin-bottom: 4px; 
          color: var(--role-color);
        }
        p { 
          margin: 0;
          opacity: 0.9; 
        }
        .mobile {
          font-size: 0.85rem;
          opacity: 0.6;
        }
      }
      
      .arrow {
        font-size: 1.5rem;
        color: var(--role-color);
        opacity: 0;
        transition: opacity 0.3s;
      }
      
      &:hover .arrow {
        opacity: 1;
      }
    }
    
    .dev-footer {
      margin-top: 32px;
      text-align: center;
      
      .back-link {
        color: #00c853;
        text-decoration: none;
        font-weight: 500;
        
        &:hover { text-decoration: underline; }
      }
      
      .warning {
        margin-top: 16px;
        color: rgba(255, 255, 255, 0.4);
        font-size: 0.85rem;
      }
    }
  `]
})
export class DevLoginComponent {
    testUsers: TestUser[] = [
        {
            role: 'Customer',
            name: 'Rahul Sharma',
            mobile: '9876543210',
            icon: '🍽️',
            color: '#071627',
            dashboard: '/user/dashboard'
        },
        {
            role: 'Home Chef',
            name: 'Priya Patel',
            mobile: '9876543211',
            icon: '👨‍🍳',
            color: '#FF8A00',
            dashboard: '/homemaker/dashboard'
        },
        {
            role: 'Delivery Partner',
            name: 'Vikram Singh',
            mobile: '9876543212',
            icon: '🚴',
            color: '#00c853',
            dashboard: '/delivery-executive/dashboard'
        },
        {
            role: 'Administrator',
            name: 'System Admin',
            mobile: 'admin',
            icon: '⚙️',
            color: '#9B59B6',
            dashboard: '/admin/dashboard'
        }
    ];

    constructor(
        private router: Router,
        private authService: AuthService
    ) { }

    quickLogin(user: TestUser): void {
        const roleKey = this.getRoleKey(user.role) as 'USER' | 'HOMEMAKER' | 'DELIVERYEXECUTIVE' | 'ADMIN';
        this.authService.devLogin(roleKey);
    }

    private getRoleKey(role: string): string {
        const roleMap: { [key: string]: string } = {
            'Customer': 'USER',
            'Home Chef': 'HOMEMAKER',
            'Delivery Partner': 'DELIVERYEXECUTIVE',
            'Administrator': 'ADMIN'
        };
        return roleMap[role] || 'USER';
    }
}
