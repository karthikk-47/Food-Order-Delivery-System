import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="unauthorized-container">
      <h1>403</h1>
      <h2>Unauthorized Access</h2>
      <p>You don't have permission to access this page.</p>
      <button routerLink="/login">Go to Login</button>
    </div>
  `,
  styles: [`
    .unauthorized-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
      text-align: center;
      padding: 20px;
    }
    h1 { font-size: 72px; color: #f44336; margin: 0; }
    h2 { font-size: 32px; margin: 16px 0; }
    p { color: #666; margin-bottom: 24px; }
    button {
      padding: 12px 32px;
      background: #667eea;
      color: white;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 16px;
    }
  `]
})
export class UnauthorizedComponent {}
