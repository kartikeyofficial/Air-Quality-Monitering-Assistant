import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { AirQualityReading } from '../models/air-quality.model';

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class AirQualityService {
  private readonly apiUrl = `${environment.apiUrl}/air-quality`;

  constructor(private http: HttpClient) {}

  getPage(
    locationId: number,
    page: number = 0,
    size: number = 10,
  ): Observable<PageResponse<AirQualityReading>> {
    const params = new HttpParams().set('page', page).set('size', size);

    return this.http.get<PageResponse<AirQualityReading>>(`${this.apiUrl}/location/${locationId}`, {
      params,
    });
  }

  getLatest(locationId: number): Observable<PageResponse<AirQualityReading>> {
    return this.getPage(locationId, 0, 1);
  }

  getById(id: number): Observable<AirQualityReading> {
    return this.http.get<AirQualityReading>(`${this.apiUrl}/${id}`);
  }
}
