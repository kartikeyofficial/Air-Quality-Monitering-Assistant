import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { AirQualityReading } from '../models/air-quality.model';

@Injectable({
  providedIn: 'root',
})
export class AirQualityService {
  private readonly apiUrl = `${environment.apiUrl}/air-quality`;

  constructor(private http: HttpClient) {}

  getLatest(locationId: number): Observable<AirQualityReading[]> {
    return this.http.get<AirQualityReading[]>(`${this.apiUrl}/location/${locationId}`);
  }

  getPage(locationId: number, page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams().set('page', page).set('size', size);

    return this.http.get<any>(`${this.apiUrl}/location/${locationId}`, { params });
  }

  getById(id: number): Observable<AirQualityReading> {
    return this.http.get<AirQualityReading>(`${this.apiUrl}/${id}`);
  }
}
