import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeliveryExecutiveService, WalletInfo } from '../services/delivery-executive.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-wallet',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  template: `
    <app-navbar></app-navbar>
    <div class="wallet-container">
      <h2>My Wallet</h2>

      <div class="wallet-summary" *ngIf="wallet">
        <div class="balance-card">
          <h3>Current Balance</h3>
          <p class="amount">₹{{ wallet.balance.toFixed(2) }}</p>
          <p class="hint">Available to withdraw</p>
        </div>

        <div class="stats-card">
          <div class="stat">
            <span class="label">Total Earnings</span>
            <span class="value">₹{{ wallet.totalEarnings.toFixed(2) }}</span>
          </div>
          <div class="stat">
            <span class="label">Pending Amount</span>
            <span class="value">₹{{ wallet.pendingAmount.toFixed(2) }}</span>
          </div>
        </div>
      </div>

      <div class="withdraw-section" *ngIf="wallet">
        <h3>Request Withdrawal</h3>
        <div class="withdraw-form">
          <label for="amount">Amount (₹)</label>
          <input
            id="amount"
            type="number"
            min="100"
            [max]="wallet.balance"
            [(ngModel)]="withdrawAmount"
            class="amount-input"
            placeholder="Enter amount to withdraw"
          />
          <div class="actions">
            <button
              class="btn-withdraw"
              (click)="withdraw()"
              [disabled]="loading || !canWithdraw()"
            >
              {{ loading ? 'Processing...' : 'Withdraw' }}
            </button>
          </div>
          <p class="error" *ngIf="error">{{ error }}</p>
          <p class="success" *ngIf="success">{{ success }}</p>
          <p class="note">Minimum withdrawal ₹{{ minWithdrawal }}. Money will be settled to your registered account.</p>
        </div>
      </div>

      <div *ngIf="loading && !wallet" class="loading">Loading wallet...</div>
    </div>
  `,
  styles: [`
    .wallet-container { padding: 24px; max-width: 1200px; margin: 0 auto; }
    .wallet-summary { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 24px; }
    .balance-card, .stats-card { background: white; padding: 24px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .amount { font-size: 36px; font-weight: 700; color: #4caf50; margin: 8px 0; }
    .stat { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid #f0f0f0; }
    .stat:last-child { border-bottom: none; }
    .stat .label { color: #666; }
    .stat .value { font-weight: 600; color: #333; }
    .hint { color: #666; margin-top: 4px; }
    .withdraw-section { margin-top: 32px; max-width: 480px; }
    .withdraw-form { background: #fff; padding: 20px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.06); display: flex; flex-direction: column; gap: 12px; }
    .amount-input { padding: 10px 12px; border-radius: 8px; border: 1px solid #ddd; font-size: 14px; }
    .actions { margin-top: 8px; }
    .btn-withdraw { padding: 10px 16px; border-radius: 999px; border: none; background: #00c853; color: #fff; font-weight: 600; cursor: pointer; }
    .btn-withdraw[disabled] { opacity: 0.6; cursor: not-allowed; }
    .error { color: #d32f2f; font-size: 13px; }
    .success { color: #2e7d32; font-size: 13px; }
    .note { font-size: 12px; color: #777; }
  `]
})
export class WalletComponent implements OnInit {
  wallet: WalletInfo | null = null;
  loading = false;
  executiveId: number = 0;
  withdrawAmount: number | null = null;
  minWithdrawal = 100;
  error = '';
  success = '';

  constructor(
    private deliveryService: DeliveryExecutiveService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) this.executiveId = user.id;
  }

  ngOnInit() {
    this.loadWallet();
  }

  loadWallet() {
    this.loading = true;
    this.deliveryService.getWallet(this.executiveId).subscribe({
      next: (wallet) => { this.wallet = wallet; this.loading = false; },
      error: () => this.loading = false
    });
  }

  canWithdraw(): boolean {
    if (!this.wallet || this.withdrawAmount == null) return false;
    return this.withdrawAmount >= this.minWithdrawal && this.withdrawAmount <= this.wallet.balance;
  }

  withdraw() {
    if (!this.canWithdraw()) {
      this.error = `Enter an amount between ₹${this.minWithdrawal} and your available balance.`;
      return;
    }

    this.loading = true;
    this.error = '';
    this.success = '';

    this.deliveryService.withdrawFromWallet(this.executiveId, this.withdrawAmount as number).subscribe({
      next: (wallet) => {
        this.wallet = wallet;
        this.loading = false;
        this.withdrawAmount = null;
        this.success = 'Withdrawal request processed. Amount will be settled shortly.';
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.error?.message || 'Failed to process withdrawal. Please try again.';
      }
    });
  }
}
