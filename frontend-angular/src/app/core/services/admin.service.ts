import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface AdminDashboardStats {
  totalUsers: number;
  totalHomemakers: number;
  totalExecutives: number;
  totalOrders: number;
  totalRevenue: number;
  activeOrders: number;
  pendingVerifications: number;
  openDisputes: number;
  pendingHomemakers?: number;
  pendingExecutives?: number;
  approvalStatistics?: ApprovalStatistics;
}

export interface ApprovalStatistics {
  pendingHomemakers: number;
  approvedHomemakers: number;
  rejectedHomemakers: number;
  pendingExecutives: number;
  approvedExecutives: number;
  rejectedExecutives: number;
  totalPending: number;
  totalApproved: number;
  totalRejected: number;
}

export interface ProfileApproval {
  id: number;
  name: string;
  mobile: string;
  userType: 'HOMEMAKER' | 'DELIVERY_EXECUTIVE';
  approvalStatus: 'PENDING' | 'APPROVED' | 'REJECTED';
  rejectionReason?: string;
  registeredAt: string;
  approvedAt?: string;
  approvedBy?: string;
  address?: string;
  aadharNo?: string;
  licenseNo?: string;
  email?: string;
  documentsVerified?: boolean;
  pendingVerifications?: number;
}

export interface Verification {
  id: number;
  actorId: number;
  actorType: string;
  actorName: string;
  verificationType: string;
  status: string;
  submittedDate: string;
  reviewedDate?: string;
  reviewedBy?: number;
  documents: VerificationDocument[];
  notes?: string;
}

export interface VerificationDocument {
  id: number;
  documentType: string;
  documentUrl: string;
  uploadedDate: string;
}

export interface Dispute {
  id: number;
  orderId: number;
  raisedBy: number;
  raisedByName: string;
  raisedByType: string;
  againstId: number;
  againstName: string;
  againstType: string;
  disputeType: string;
  status: string;
  description: string;
  raisedDate: string;
  resolvedDate?: string;
  resolvedBy?: number;
  resolution?: string;
  refundAmount?: number;
}

export interface PlatformAnalytics {
  totalRevenue: number;
  totalCommission: number;
  averageOrderValue: number;
  orderGrowthRate: number;
  userGrowthRate: number;
  homemakerGrowthRate: number;
  executiveGrowthRate: number;
  topHomemakers: TopPerformer[];
  topExecutives: TopPerformer[];
  revenueByMonth: MonthlyRevenue[];
}

export interface TopPerformer {
  id: number;
  name: string;
  totalOrders: number;
  totalRevenue: number;
  rating: number;
}

export interface MonthlyRevenue {
  month: string;
  revenue: number;
  orders: number;
  commission: number;
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = `${environment.apiUrl}/admin`;
  private approvalUrl = `${environment.apiUrl}/admin/approvals`;

  constructor(private http: HttpClient) { }

  // Dashboard
  getDashboardStats(): Observable<AdminDashboardStats> {
    return this.http.get<AdminDashboardStats>(`${this.apiUrl}/analytics/dashboard`);
  }

  // Verification Management
  getVerifications(status?: string, type?: string): Observable<Verification[]> {
    let params = new HttpParams();
    if (status) params = params.set('status', status);
    if (type) params = params.set('type', type);
    return this.http.get<Verification[]>(`${this.apiUrl}/verifications`, { params });
  }

  getVerificationById(verificationId: number): Observable<Verification> {
    return this.http.get<Verification>(`${this.apiUrl}/verifications/${verificationId}`);
  }

  approveVerification(verificationId: number, notes?: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/verifications/${verificationId}/approve`, { notes });
  }

  rejectVerification(verificationId: number, reason: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/verifications/${verificationId}/reject`, { reason });
  }

  // Dispute Management
  getDisputes(status?: string): Observable<Dispute[]> {
    let params = new HttpParams();
    if (status) params = params.set('status', status);
    return this.http.get<Dispute[]>(`${this.apiUrl}/disputes`, { params });
  }

  getDisputeById(disputeId: number): Observable<Dispute> {
    return this.http.get<Dispute>(`${this.apiUrl}/disputes/${disputeId}`);
  }

  resolveDispute(disputeId: number, resolution: string, refundAmount?: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/disputes/${disputeId}/resolve`, {
      resolution,
      refundAmount
    });
  }

  escalateDispute(disputeId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/disputes/${disputeId}/escalate`, {});
  }

  // Analytics
  getPlatformAnalytics(period?: string): Observable<PlatformAnalytics> {
    let params = new HttpParams();
    if (period) params = params.set('period', period);
    return this.http.get<PlatformAnalytics>(`${this.apiUrl}/analytics`, { params });
  }

  // User Management
  getAllUsers(page: number = 0, size: number = 20): Observable<any> {
    return this.http.get(`${this.apiUrl}/users`, {
      params: { page: page.toString(), size: size.toString() }
    });
  }

  getAllHomemakers(page: number = 0, size: number = 20): Observable<any> {
    return this.http.get(`${this.apiUrl}/homemakers`, {
      params: { page: page.toString(), size: size.toString() }
    });
  }

  getAllExecutives(page: number = 0, size: number = 20): Observable<any> {
    return this.http.get(`${this.apiUrl}/executives`, {
      params: { page: page.toString(), size: size.toString() }
    });
  }

  suspendUser(userId: number, reason: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/users/${userId}/suspend`, { reason });
  }

  activateUser(userId: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/users/${userId}/activate`, {});
  }

  // ============ PROFILE APPROVAL MANAGEMENT ============

  // Approval Statistics
  getApprovalStatistics(): Observable<ApprovalStatistics> {
    return this.http.get<ApprovalStatistics>(`${this.approvalUrl}/statistics`);
  }

  getAllPendingApprovals(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/pending/all`);
  }

  // Homemaker Approvals
  getPendingHomemakerApprovals(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/homemakers/pending`);
  }

  getApprovedHomemakers(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/homemakers/approved`);
  }

  getRejectedHomemakers(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/homemakers/rejected`);
  }

  approveHomemaker(id: number, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/homemakers/${id}/approve?adminId=${adminId}`, {});
  }

  rejectHomemaker(id: number, reason: string, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/homemakers/${id}/reject?adminId=${adminId}`, { reason });
  }

  revertHomemakerToPending(id: number, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/homemakers/${id}/revert?adminId=${adminId}`, {});
  }

  bulkApproveHomemakers(ids: number[], adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/homemakers/bulk-approve?adminId=${adminId}`, ids);
  }

  getHomemakerApprovalStatus(id: number): Observable<any> {
    return this.http.get<any>(`${this.approvalUrl}/homemakers/${id}/status`);
  }

  // Delivery Executive Approvals
  getPendingExecutiveApprovals(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/executives/pending`);
  }

  getApprovedExecutives(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/executives/approved`);
  }

  getRejectedExecutives(): Observable<ProfileApproval[]> {
    return this.http.get<ProfileApproval[]>(`${this.approvalUrl}/executives/rejected`);
  }

  approveExecutive(id: number, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/executives/${id}/approve?adminId=${adminId}`, {});
  }

  rejectExecutive(id: number, reason: string, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/executives/${id}/reject?adminId=${adminId}`, { reason });
  }

  revertExecutiveToPending(id: number, adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/executives/${id}/revert?adminId=${adminId}`, {});
  }

  bulkApproveExecutives(ids: number[], adminId: number = 1): Observable<any> {
    return this.http.put<any>(`${this.approvalUrl}/executives/bulk-approve?adminId=${adminId}`, ids);
  }

  getExecutiveApprovalStatus(id: number): Observable<any> {
    return this.http.get<any>(`${this.approvalUrl}/executives/${id}/status`);
  }
}