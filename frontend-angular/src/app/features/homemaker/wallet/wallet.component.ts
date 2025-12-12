import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HomemakerService } from '../services/homemaker.service';
import { ReplacePipe } from '../../../shared/pipes/replace.pipe';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-homemaker-wallet',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, ReplacePipe, NavbarComponent],
  providers: [HomemakerService],
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.scss']
})
export class WalletComponent implements OnInit {
  homemakerId: number = 0;
  wallet: any = null;
  withdrawalHistory: any[] = [];
  withdrawalForm: FormGroup;
  
  showWithdrawalForm = false;
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';
  
  withdrawalMethods = ['BANK_TRANSFER', 'UPI', 'CHEQUE'];
  selectedMethod = 'BANK_TRANSFER';
  
  minWithdrawalAmount = 500;
  maxWithdrawalAmount = 100000;
  
  activeTab = 'overview'; // overview, history, withdraw

  constructor(
    private formBuilder: FormBuilder,
    private homemakerService: HomemakerService
  ) {
    this.withdrawalForm = this.createWithdrawalForm();
  }

  ngOnInit() {
    this.getUserData();
    this.loadWallet();
    this.loadWithdrawalHistory();
  }

  getUserData() {
    const userData = localStorage.getItem('user');
    if (userData) {
      try {
        const user = JSON.parse(userData);
        this.homemakerId = user.id;
      } catch (e) {
        console.error('Error parsing user data', e);
        this.errorMessage = 'Unable to identify user. Please login again.';
      }
    }
  }

  loadWallet() {
    if (!this.homemakerId) return;
    
    this.homemakerService.getWallet(this.homemakerId).subscribe({
      next: (data) => {
        this.wallet = data;
      },
      error: (err) => {
        console.error('Error loading wallet:', err);
        this.errorMessage = 'Failed to load wallet information.';
      }
    });
  }

  loadWithdrawalHistory() {
    if (!this.homemakerId) return;
    
    this.homemakerService.getWithdrawalHistory(this.homemakerId).subscribe({
      next: (data) => {
        this.withdrawalHistory = data;
      },
      error: (err) => {
        console.error('Error loading withdrawal history:', err);
      }
    });
  }

  createWithdrawalForm(): FormGroup {
    return this.formBuilder.group({
      amount: ['', [Validators.required, Validators.min(this.minWithdrawalAmount), Validators.max(this.maxWithdrawalAmount)]],
      method: [this.selectedMethod, Validators.required],
      bankAccountNumber: [''],
      bankIFSC: [''],
      upiId: [''],
      chequeNumber: ['']
    });
  }

  onMethodChange(method: string) {
    this.selectedMethod = method;
    this.withdrawalForm.patchValue({ method });
    this.updateValidators();
  }

  updateValidators() {
    const bankAccountControl = this.withdrawalForm.get('bankAccountNumber');
    const bankIFSCControl = this.withdrawalForm.get('bankIFSC');
    const upiIdControl = this.withdrawalForm.get('upiId');
    const chequeNumberControl = this.withdrawalForm.get('chequeNumber');

    // Clear all validators
    [bankAccountControl, bankIFSCControl, upiIdControl, chequeNumberControl].forEach(control => {
      control?.clearValidators();
      control?.updateValueAndValidity({ emitEvent: false });
    });

    // Add validators based on selected method
    if (this.selectedMethod === 'BANK_TRANSFER') {
      bankAccountControl?.setValidators([Validators.required, Validators.minLength(9)]);
      bankIFSCControl?.setValidators([Validators.required, Validators.minLength(11)]);
    } else if (this.selectedMethod === 'UPI') {
      upiIdControl?.setValidators([Validators.required, Validators.pattern(/^[a-zA-Z0-9._-]+@[a-zA-Z]{2,}$/)]);
    } else if (this.selectedMethod === 'CHEQUE') {
      chequeNumberControl?.setValidators([Validators.required, Validators.minLength(10)]);
    }

    // Update validity
    [bankAccountControl, bankIFSCControl, upiIdControl, chequeNumberControl].forEach(control => {
      control?.updateValueAndValidity({ emitEvent: false });
    });
  }

  requestWithdrawal() {
    if (!this.withdrawalForm.valid) {
      this.errorMessage = 'Please fill in all required fields correctly.';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';
    this.successMessage = '';

    const withdrawalData = {
      ...this.withdrawalForm.value,
      homemakerId: this.homemakerId
    };

    this.homemakerService.requestWithdrawal(this.homemakerId, withdrawalData).subscribe({
      next: (response) => {
        this.isSubmitting = false;
        this.successMessage = 'Withdrawal request submitted successfully!';
        this.withdrawalForm.reset();
        this.showWithdrawalForm = false;
        this.loadWallet();
        this.loadWithdrawalHistory();
        
        setTimeout(() => {
          this.successMessage = '';
        }, 5000);
      },
      error: (error) => {
        this.isSubmitting = false;
        console.error('Error requesting withdrawal:', error);
        this.errorMessage = error.error?.message || 'Failed to request withdrawal. Please try again.';
      }
    });
  }

  setTab(tab: string) {
    this.activeTab = tab;
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'COMPLETED':
        return 'success';
      case 'PENDING':
        return 'warning';
      case 'APPROVED':
        return 'info';
      case 'REJECTED':
        return 'danger';
      default:
        return 'secondary';
    }
  }

  getStatusIcon(status: string): string {
    switch (status) {
      case 'COMPLETED':
        return '✓';
      case 'PENDING':
        return '⏳';
      case 'APPROVED':
        return '✓';
      case 'REJECTED':
        return '✗';
      default:
        return '•';
    }
  }

  formatDate(date: string): string {
    if (!date) return 'N/A';
    return new Date(date).toLocaleDateString('en-IN', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  cancelWithdrawal() {
    this.showWithdrawalForm = false;
    this.withdrawalForm.reset();
    this.errorMessage = '';
  }
}
