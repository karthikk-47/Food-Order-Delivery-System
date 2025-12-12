import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface BankDetails {
  id?: number;
  userId: number;
  accountHolderName: string;
  accountNumber: string;
  ifscCode: string;
  bankName: string;
  branchName?: string;
  accountType: 'SAVINGS' | 'CURRENT';
  isPrimary: boolean;
  isVerified: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class BankDetailsService {
  private apiUrl = `${environment.apiUrl}/bank-details`;

  constructor(private http: HttpClient) {}

  // Get all bank accounts for the current user
  getBankAccounts(userId: number): Observable<BankDetails[]> {
    return this.http.get<BankDetails[]>(`${this.apiUrl}/user/${userId}`);
  }

  // Get a specific bank account
  getBankAccount(accountId: number): Observable<BankDetails> {
    return this.http.get<BankDetails>(`${this.apiUrl}/${accountId}`);
  }

  // Add a new bank account
  addBankAccount(account: Omit<BankDetails, 'id' | 'isVerified'>): Observable<BankDetails> {
    return this.http.post<BankDetails>(this.apiUrl, account);
  }

  // Update an existing bank account
  updateBankAccount(accountId: number, updates: Partial<BankDetails>): Observable<BankDetails> {
    return this.http.put<BankDetails>(`${this.apiUrl}/${accountId}`, updates);
  }

  // Delete a bank account
  deleteBankAccount(accountId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${accountId}`);
  }

  // Set an account as primary
  setPrimaryAccount(accountId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${accountId}/set-primary`, {});
  }

  // Verify IFSC code with bank
  verifyIfsc(ifscCode: string): Observable<{bank: string, branch: string, ifsc: string}> {
    return this.http.get<{bank: string, branch: string, ifsc: string}>(`${this.apiUrl}/verify-ifsc/${ifscCode}`);
  }
}
