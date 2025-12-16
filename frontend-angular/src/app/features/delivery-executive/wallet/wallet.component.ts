import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DeliveryExecutiveService, WalletInfo } from '../services/delivery-executive.service';
import { AuthService } from '../../../core/services/auth.service';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

interface BankAccount {
  id: number;
  accountHolderName: string;
  accountNumberMasked: string;
  ifscCode: string;
  bankName: string;
  branchName?: string;
  accountType: string;
  isPrimary: boolean;
  isVerified: boolean;
}

@Component({
  selector: 'app-wallet',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NavbarComponent],
  template: `
    <app-navbar></app-navbar>
    <div class="wallet-container">
      <h2>💰 My Wallet</h2>

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

      <!-- Bank Accounts Section -->
      <div class="bank-section">
        <div class="section-header">
          <h3>🏦 Bank Accounts</h3>
          <button class="btn-add" (click)="showAddBankForm = true" *ngIf="!showAddBankForm">
            + Add Bank Account
          </button>
        </div>

        <!-- Add Bank Account Form -->
        <div class="bank-form" *ngIf="showAddBankForm">
          <h4>Add New Bank Account</h4>
          <div class="form-group">
            <label>Account Holder Name</label>
            <input type="text" [(ngModel)]="newBank.accountHolderName" placeholder="Enter name as per bank">
          </div>
          <div class="form-group">
            <label>Account Number</label>
            <input type="text" [(ngModel)]="newBank.accountNumber" placeholder="Enter account number">
          </div>
          <div class="form-group">
            <label>IFSC Code</label>
            <input type="text" [(ngModel)]="newBank.ifscCode" placeholder="e.g., SBIN0001234"
                   (input)="newBank.ifscCode = newBank.ifscCode.toUpperCase()">
          </div>
          <div class="form-group">
            <label>Bank Name</label>
            <input type="text" [(ngModel)]="newBank.bankName" placeholder="Enter bank name">
          </div>
          <div class="form-group">
            <label>Branch Name (Optional)</label>
            <input type="text" [(ngModel)]="newBank.branchName" placeholder="Enter branch name">
          </div>
          <div class="form-group">
            <label>Account Type</label>
            <select [(ngModel)]="newBank.accountType">
              <option value="savings">Savings</option>
              <option value="current">Current</option>
            </select>
          </div>
          <div class="form-actions">
            <button class="btn-cancel" (click)="cancelAddBank()">Cancel</button>
            <button class="btn-save" (click)="addBankAccount()" [disabled]="!isValidBankForm()">
              Save Bank Account
            </button>
          </div>
          <p class="error" *ngIf="bankError">{{ bankError }}</p>
        </div>

        <!-- Bank Accounts List -->
        <div class="bank-list" *ngIf="bankAccounts.length > 0 && !showAddBankForm">
          <div class="bank-card" *ngFor="let account of bankAccounts" [class.primary]="account.isPrimary">
            <div class="bank-info">
              <div class="bank-name">{{ account.bankName }}</div>
              <div class="account-number">{{ account.accountNumberMasked }}</div>
              <div class="account-holder">{{ account.accountHolderName }}</div>
              <div class="ifsc">IFSC: {{ account.ifscCode }}</div>
            </div>
            <div class="bank-actions">
              <span class="primary-badge" *ngIf="account.isPrimary">Primary</span>
              <button class="btn-set-primary" (click)="setPrimaryAccount(account.id)" *ngIf="!account.isPrimary">
                Set Primary
              </button>
              <button class="btn-delete" (click)="deleteBankAccount(account.id)" *ngIf="!account.isPrimary">
                🗑️
              </button>
            </div>
          </div>
        </div>

        <div class="no-accounts" *ngIf="bankAccounts.length === 0 && !showAddBankForm">
          <p>No bank accounts added yet. Add a bank account to withdraw your earnings.</p>
        </div>
      </div>

      <!-- Withdraw Section -->
      <div class="withdraw-section" *ngIf="wallet && bankAccounts.length > 0">
        <h3>💸 Request Withdrawal</h3>
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
              {{ loading ? 'Processing...' : 'Withdraw to Bank' }}
            </button>
          </div>
          <p class="error" *ngIf="error">{{ error }}</p>
          <p class="success" *ngIf="success">{{ success }}</p>
          <p class="note">Minimum withdrawal ₹{{ minWithdrawal }}. Money will be credited to your primary bank account.</p>
        </div>
      </div>

      <div class="no-bank-warning" *ngIf="wallet && bankAccounts.length === 0">
        <p>⚠️ Please add a bank account to withdraw your earnings.</p>
      </div>

      <div *ngIf="loading && !wallet" class="loading">Loading wallet...</div>
    </div>
  `,
  styles: [`
    .wallet-container { padding: 24px; max-width: 900px; margin: 0 auto; }
    h2 { margin-bottom: 24px; }
    .wallet-summary { display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 24px; margin-bottom: 32px; }
    .balance-card, .stats-card { background: white; padding: 24px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .amount { font-size: 36px; font-weight: 700; color: #4caf50; margin: 8px 0; }
    .stat { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid #f0f0f0; }
    .stat:last-child { border-bottom: none; }
    .stat .label { color: #666; }
    .stat .value { font-weight: 600; color: #333; }
    .hint { color: #666; margin-top: 4px; font-size: 14px; }
    
    .bank-section { background: white; padding: 24px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); margin-bottom: 24px; }
    .section-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; }
    .section-header h3 { margin: 0; }
    .btn-add { background: #667eea; color: white; border: none; padding: 8px 16px; border-radius: 8px; cursor: pointer; }
    
    .bank-form { background: #f8f9fa; padding: 20px; border-radius: 8px; }
    .bank-form h4 { margin: 0 0 16px; }
    .form-group { margin-bottom: 16px; }
    .form-group label { display: block; margin-bottom: 6px; font-weight: 500; color: #333; }
    .form-group input, .form-group select { width: 100%; padding: 10px 12px; border: 1px solid #ddd; border-radius: 8px; font-size: 14px; box-sizing: border-box; }
    .form-actions { display: flex; gap: 12px; margin-top: 20px; }
    .btn-cancel { background: #f0f0f0; color: #666; border: none; padding: 10px 20px; border-radius: 8px; cursor: pointer; }
    .btn-save { background: #4caf50; color: white; border: none; padding: 10px 20px; border-radius: 8px; cursor: pointer; }
    .btn-save:disabled { opacity: 0.6; cursor: not-allowed; }
    
    .bank-list { display: flex; flex-direction: column; gap: 12px; }
    .bank-card { display: flex; justify-content: space-between; align-items: center; padding: 16px; background: #f8f9fa; border-radius: 8px; border: 2px solid transparent; }
    .bank-card.primary { border-color: #4caf50; background: #f0fff4; }
    .bank-info { flex: 1; }
    .bank-name { font-weight: 600; font-size: 16px; }
    .account-number { color: #333; margin: 4px 0; }
    .account-holder { color: #666; font-size: 14px; }
    .ifsc { color: #999; font-size: 12px; }
    .bank-actions { display: flex; align-items: center; gap: 12px; }
    .primary-badge { background: #4caf50; color: white; padding: 4px 12px; border-radius: 12px; font-size: 12px; }
    .btn-set-primary { background: #e3f2fd; color: #1976d2; border: none; padding: 6px 12px; border-radius: 6px; cursor: pointer; font-size: 12px; }
    .btn-delete { background: none; border: none; cursor: pointer; font-size: 18px; }
    
    .no-accounts { text-align: center; padding: 24px; color: #666; }
    
    .withdraw-section { background: white; padding: 24px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
    .withdraw-section h3 { margin: 0 0 16px; }
    .withdraw-form { display: flex; flex-direction: column; gap: 12px; }
    .amount-input { padding: 12px; border-radius: 8px; border: 1px solid #ddd; font-size: 16px; }
    .actions { margin-top: 8px; }
    .btn-withdraw { padding: 12px 24px; border-radius: 8px; border: none; background: #00c853; color: #fff; font-weight: 600; cursor: pointer; font-size: 16px; }
    .btn-withdraw:disabled { opacity: 0.6; cursor: not-allowed; }
    .error { color: #d32f2f; font-size: 14px; margin: 8px 0; }
    .success { color: #2e7d32; font-size: 14px; margin: 8px 0; }
    .note { font-size: 13px; color: #777; }
    
    .no-bank-warning { background: #fff3cd; padding: 16px; border-radius: 8px; text-align: center; color: #856404; }
    .loading { text-align: center; padding: 40px; color: #666; }
  `]
})
export class WalletComponent implements OnInit {
  wallet: WalletInfo | null = null;
  bankAccounts: BankAccount[] = [];
  loading = false;
  executiveId: number = 0;
  withdrawAmount: number | null = null;
  minWithdrawal = 100;
  error = '';
  success = '';
  bankError = '';
  showAddBankForm = false;

  newBank = {
    accountHolderName: '',
    accountNumber: '',
    ifscCode: '',
    bankName: '',
    branchName: '',
    accountType: 'savings',
    isPrimary: true
  };

  constructor(
    private deliveryService: DeliveryExecutiveService,
    private authService: AuthService
  ) {
    const user = this.authService.currentUserValue;
    if (user) this.executiveId = user.id;
  }

  ngOnInit() {
    this.loadWallet();
    this.loadBankAccounts();
  }

  loadWallet() {
    this.loading = true;
    this.deliveryService.getWallet(this.executiveId).subscribe({
      next: (wallet) => { this.wallet = wallet; this.loading = false; },
      error: () => this.loading = false
    });
  }

  loadBankAccounts() {
    this.deliveryService.getBankAccounts().subscribe({
      next: (accounts) => this.bankAccounts = accounts,
      error: () => this.bankAccounts = []
    });
  }

  isValidBankForm(): boolean {
    return !!(
      this.newBank.accountHolderName &&
      this.newBank.accountNumber &&
      this.newBank.ifscCode &&
      this.newBank.bankName &&
      this.newBank.accountNumber.match(/^[0-9]{9,18}$/) &&
      this.newBank.ifscCode.match(/^[A-Z]{4}0[A-Z0-9]{6}$/)
    );
  }

  addBankAccount() {
    if (!this.isValidBankForm()) {
      this.bankError = 'Please fill all required fields correctly';
      return;
    }

    this.bankError = '';
    this.deliveryService.addBankAccount(this.newBank).subscribe({
      next: () => {
        this.loadBankAccounts();
        this.cancelAddBank();
      },
      error: (err) => {
        this.bankError = err?.error?.message || 'Failed to add bank account';
      }
    });
  }

  cancelAddBank() {
    this.showAddBankForm = false;
    this.newBank = {
      accountHolderName: '',
      accountNumber: '',
      ifscCode: '',
      bankName: '',
      branchName: '',
      accountType: 'savings',
      isPrimary: true
    };
    this.bankError = '';
  }

  setPrimaryAccount(accountId: number) {
    this.deliveryService.setPrimaryBankAccount(accountId).subscribe({
      next: () => this.loadBankAccounts(),
      error: () => alert('Failed to set primary account')
    });
  }

  deleteBankAccount(accountId: number) {
    if (confirm('Are you sure you want to delete this bank account?')) {
      this.deliveryService.deleteBankAccount(accountId).subscribe({
        next: () => this.loadBankAccounts(),
        error: () => alert('Failed to delete bank account')
      });
    }
  }

  canWithdraw(): boolean {
    if (!this.wallet || this.withdrawAmount == null || this.bankAccounts.length === 0) return false;
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
        this.success = 'Withdrawal request processed. Amount will be credited to your bank account within 24-48 hours.';
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.error?.message || 'Failed to process withdrawal. Please try again.';
      }
    });
  }
}
