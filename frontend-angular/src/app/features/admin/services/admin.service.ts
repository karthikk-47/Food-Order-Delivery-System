import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = `${environment.apiUrl}/admin`;
  private manageUrl = `${environment.apiUrl}/admin/manage`;
  private analyticsUrl = `${environment.apiUrl}/admin/analytics`;
  private approvalUrl = `${environment.apiUrl}/admin/approvals`;

  constructor(private http: HttpClient) { }

  // User Management
  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/users`);
  }

  verifyUser(id: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/users/${id}/verify`, {});
  }

  suspendUser(id: number, reason: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/users/${id}/suspend`, { reason });
  }

  // Homemaker Management
  getAllHomemakers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/homemakers`);
  }

  verifyHomemaker(id: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/homemakers/${id}/verify`, {});
  }

  // Delivery Executive Management
  getAllExecutives(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/executives`);
  }

  verifyExecutive(id: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/executives/${id}/verify`, {});
  }

  // Analytics
  getDashboardStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/analytics/overview`);
  }

  getRevenueStats(period: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/analytics/revenue?period=${period}`);
  }

  // Disputes
  getAllDisputes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/disputes`);
  }

  resolveDispute(id: number, resolution: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/disputes/${id}/resolve`, { resolution });
  }

  // ============ APPROVAL MANAGEMENT (New ProfileApprovalController endpoints) ============

  // Homemaker Approvals
  getPendingHomemakerApprovals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/homemakers/pending`);
  }

  getApprovedHomemakers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/homemakers/approved`);
  }

  getRejectedHomemakers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/homemakers/rejected`);
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
  getPendingExecutiveApprovals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/executives/pending`);
  }

  getApprovedExecutives(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/executives/approved`);
  }

  getRejectedExecutives(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/executives/rejected`);
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

  // Combined Approval Operations
  getAllPendingApprovals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.approvalUrl}/pending/all`);
  }

  getApprovalStatistics(): Observable<any> {
    return this.http.get<any>(`${this.approvalUrl}/statistics`);
  }

  // ============ NEW MANAGEMENT ENDPOINTS (AdminManagementController) ============

  // User Management
  getUsers(search?: string, page: number = 0, size: number = 10): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (search) {
      params = params.set('search', search);
    }
    
    return this.http.get<any>(`${this.manageUrl}/users`, { params });
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.manageUrl}/users/${id}`);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${this.manageUrl}/users/${id}`);
  }

  // Homemaker Management
  getHomemakers(status?: string, search?: string, page: number = 0, size: number = 10): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (status) {
      params = params.set('status', status);
    }
    if (search) {
      params = params.set('search', search);
    }
    
    return this.http.get<any>(`${this.manageUrl}/homemakers`, { params });
  }

  getHomemakerById(id: number): Observable<any> {
    return this.http.get<any>(`${this.manageUrl}/homemakers/${id}`);
  }

  updateHomemakerApprovalStatus(id: number, status: string, reason?: string): Observable<any> {
    const body: any = { status };
    if (reason) {
      body.reason = reason;
    }
    return this.http.put<any>(`${this.manageUrl}/homemakers/${id}/approval-status`, body);
  }

  deleteHomemaker(id: number): Observable<any> {
    return this.http.delete<any>(`${this.manageUrl}/homemakers/${id}`);
  }

  // Delivery Executive Management
  getExecutives(status?: string, search?: string, page: number = 0, size: number = 10): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (status) {
      params = params.set('status', status);
    }
    if (search) {
      params = params.set('search', search);
    }
    
    return this.http.get<any>(`${this.manageUrl}/executives`, { params });
  }

  getExecutiveById(id: number): Observable<any> {
    return this.http.get<any>(`${this.manageUrl}/executives/${id}`);
  }

  updateExecutiveApprovalStatus(id: number, status: string, reason?: string): Observable<any> {
    const body: any = { status };
    if (reason) {
      body.reason = reason;
    }
    return this.http.put<any>(`${this.manageUrl}/executives/${id}/approval-status`, body);
  }

  deleteExecutive(id: number): Observable<any> {
    return this.http.delete<any>(`${this.manageUrl}/executives/${id}`);
  }

  // ============ NEW ANALYTICS ENDPOINTS (AdminAnalyticsController) ============

  getDashboardAnalytics(): Observable<any> {
    return this.http.get<any>(`${this.analyticsUrl}/dashboard`);
  }

  getUserAnalytics(): Observable<any> {
    return this.http.get<any>(`${this.analyticsUrl}/users`);
  }

  getHomemakerAnalytics(): Observable<any> {
    return this.http.get<any>(`${this.analyticsUrl}/homemakers`);
  }

  getExecutiveAnalytics(): Observable<any> {
    return this.http.get<any>(`${this.analyticsUrl}/executives`);
  }

  getOrderAnalytics(): Observable<any> {
    return this.http.get<any>(`${this.analyticsUrl}/orders`);
  }

  // ============ ACTIVITY LOGS ============

  getLogs(page: number = 0, size: number = 50, level?: string, category?: string, search?: string): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (level) {
      params = params.set('level', level);
    }
    if (category) {
      params = params.set('category', category);
    }
    if (search) {
      params = params.set('search', search);
    }
    
    return this.http.get<any>(`${this.apiUrl}/logs`, { params });
  }

  getLogStatistics(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/logs/statistics`);
  }

  getLogLevels(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/logs/levels`);
  }

  getLogCategories(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/logs/categories`);
  }

  cleanupLogs(daysToKeep: number = 30): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/logs/cleanup?daysToKeep=${daysToKeep}`);
  }
}


