import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BankDetails, BankDetailsService } from '../../../core/services/bank-details.service';

@Component({
  selector: 'app-bank-account-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <form [formGroup]="bankForm" (ngSubmit)="onSubmit()" class="bank-account-form">
      <h3>{{ isEditMode ? 'Edit' : 'Add' }} Bank Account</h3>
      
      <div class="form-group">
        <label>Account Holder Name</label>
        <input type="text" formControlName="accountHolderName" class="form-control" 
               [class.is-invalid]="f.accountHolderName.invalid && f.accountHolderName.touched">
        <div *ngIf="f.accountHolderName.invalid && f.accountHolderName.touched" class="invalid-feedback">
          Account holder name is required
        </div>
      </div>

      <div class="form-group">
        <label>Account Number</label>
        <input type="text" formControlName="accountNumber" class="form-control"
               [class.is-invalid]="f.accountNumber.invalid && f.accountNumber.touched">
        <div *ngIf="f.accountNumber.invalid && f.accountNumber.touched" class="invalid-feedback">
          Valid account number is required
        </div>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label>IFSC Code</label>
          <input type="text" formControlName="ifscCode" class="form-control" 
                 (blur)="onIfscBlur()"
                 [class.is-invalid]="f.ifscCode.invalid && f.ifscCode.touched">
          <div *ngIf="f.ifscCode.invalid && f.ifscCode.touched" class="invalid-feedback">
            Valid IFSC code is required
          </div>
        </div>
        <div class="form-group">
          <label>Account Type</label>
          <select formControlName="accountType" class="form-control">
            <option value="SAVINGS">Savings</option>
            <option value="CURRENT">Current</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label>Bank Name</label>
        <input type="text" formControlName="bankName" class="form-control"
               [class.is-invalid]="f.bankName.invalid && f.bankName.touched">
        <div *ngIf="f.bankName.invalid && f.bankName.touched" class="invalid-feedback">
          Bank name is required
        </div>
      </div>

      <div class="form-group">
        <label>Branch Name</label>
        <input type="text" formControlName="branchName" class="form-control">
      </div>

      <div class="form-group form-check">
        <input type="checkbox" formControlName="isPrimary" class="form-check-input" id="isPrimary">
        <label class="form-check-label" for="isPrimary">Set as primary account</label>
      </div>

      <div class="form-actions">
        <button type="button" class="btn btn-outline-secondary" (click)="onCancel()">
          Cancel
        </button>
        <button type="submit" class="btn btn-primary" [disabled]="bankForm.invalid || loading">
          <span *ngIf="loading" class="spinner-border spinner-border-sm"></span>
          {{ isEditMode ? 'Update' : 'Add' }} Account
        </button>
      </div>
    </form>
  `,
  styles: [`
    .bank-account-form {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .form-group {
      margin-bottom: 1rem;
    }
    .form-row {
      display: flex;
      gap: 1rem;
    }
    .form-row .form-group {
      flex: 1;
    }
    .form-actions {
      display: flex;
      justify-content: flex-end;
      gap: 10px;
      margin-top: 1.5rem;
    }
    .invalid-feedback {
      color: #dc3545;
      font-size: 0.875em;
      margin-top: 0.25rem;
    }
    .is-invalid {
      border-color: #dc3545;
    }
  `]
})
export class BankAccountFormComponent implements OnInit {
  @Input() userId!: number;
  @Input() bankAccount?: BankDetails;
  @Output() saved = new EventEmitter<BankDetails>();
  @Output() cancelled = new EventEmitter<void>();

  bankForm: FormGroup;
  loading = false;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private bankService: BankDetailsService
  ) {
    this.bankForm = this.createForm();
  }

  ngOnInit() {
    if (this.bankAccount) {
      this.isEditMode = true;
      this.populateForm(this.bankAccount);
    }
  }

  get f() { 
    return this.bankForm.controls; 
  }

  private createForm(): FormGroup {
    return this.fb.group({
      accountHolderName: ['', [Validators.required, Validators.minLength(3)]],
      accountNumber: ['', [Validators.required, Validators.pattern('^[0-9]{9,18}$')]],
      ifscCode: ['', [Validators.required, Validators.pattern('^[A-Z]{4}0[A-Z0-9]{6}$')]],
      bankName: ['', Validators.required],
      branchName: [''],
      accountType: ['SAVINGS', Validators.required],
      isPrimary: [false]
    });
  }

  private populateForm(account: BankDetails): void {
    this.bankForm.patchValue({
      accountHolderName: account.accountHolderName,
      accountNumber: account.accountNumber,
      ifscCode: account.ifscCode,
      bankName: account.bankName,
      branchName: account.branchName || '',
      accountType: account.accountType,
      isPrimary: account.isPrimary
    });
  }

  onIfscBlur(): void {
    const ifscCode = this.bankForm.get('ifscCode')?.value;
    if (ifscCode && this.bankForm.get('ifscCode')?.valid) {
      this.loading = true;
      this.bankService.verifyIfsc(ifscCode).subscribe({
        next: (response) => {
          this.bankForm.patchValue({
            bankName: response.bank,
            branchName: response.branch
          });
          this.loading = false;
        },
        error: () => {
          this.loading = false;
        }
      });
    }
  }

  onSubmit(): void {
    if (this.bankForm.invalid) {
      this.markFormGroupTouched(this.bankForm);
      return;
    }

    this.loading = true;
    const formData = this.bankForm.value;
    const bankData: Omit<BankDetails, 'id' | 'isVerified' | 'userId'> = {
      accountHolderName: formData.accountHolderName,
      accountNumber: formData.accountNumber,
      ifscCode: formData.ifscCode,
      bankName: formData.bankName,
      branchName: formData.branchName,
      accountType: formData.accountType,
      isPrimary: formData.isPrimary
    };

    const request$ = this.isEditMode 
      ? this.bankService.updateBankAccount(this.bankAccount!.id!, bankData)
      : this.bankService.addBankAccount({ ...bankData, userId: this.userId });

    request$.subscribe({
      next: (result) => {
        this.loading = false;
        this.saved.emit(result);
      },
      error: (error) => {
        console.error('Error saving bank account:', error);
        this.loading = false;
        // Handle error (show error message)
      }
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}
