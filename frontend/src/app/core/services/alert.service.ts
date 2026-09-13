import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Alert } from '../models/alert.model';

@Injectable({
  providedIn: 'root',
})
export class AlertService {
  private readonly apiUrl = `${environment.apiUrl}/alerts`;

  constructor(private http: HttpClient) {}

  getUserAlerts(userId: number): Observable<Alert[]> {
    return this.http.get<Alert[]>(`${this.apiUrl}/user/${userId}`);
  }

  getLocationAlerts(locationId: number): Observable<Alert[]> {
    return this.http.get<Alert[]>(`${this.apiUrl}/location/${locationId}`);
  }

  markAsRead(id: number): Observable<Alert> {
    return this.http.put<Alert>(`${this.apiUrl}/${id}/read`, {});
  }
}
