import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = '/api';

  constructor(private http: HttpClient) {}

  // Delivery Executive Endpoints
  registerExecutive(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/executives/register`, data);
  }

  loginExecutive(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/executives/login`, data);
  }

  getExecutiveProfile(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/executives/${id}/profile`);
  }

  updateExecutiveStatus(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/executives/${id}/status`, data);
  }

  getNearbyOrders(id: number, lat: number, lon: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/executives/${id}/orders/nearby`, {
      params: { lat, lon },
    });
  }

  getWallet(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/executives/${id}/wallet`);
  }

  // Generic GET request
  get(endpoint: string, params?: any): Observable<any> {
    return this.http.get(`${this.apiUrl}${endpoint}`, { params });
  }

  // Generic POST request
  post(endpoint: string, data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}${endpoint}`, data);
  }

  // Generic PUT request
  put(endpoint: string, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}${endpoint}`, data);
  }

  // Generic DELETE request
  delete(endpoint: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}${endpoint}`);
  }
}
