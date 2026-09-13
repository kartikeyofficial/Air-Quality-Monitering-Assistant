import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Prediction } from '../models/prediction.model';

@Injectable({
  providedIn: 'root',
})
export class PredictionService {
  private readonly apiUrl = `${environment.apiUrl}/predictions`;

  constructor(private http: HttpClient) {}

  getByLocation(locationId: number): Observable<Prediction[]> {
    return this.http.get<Prediction[]>(`${this.apiUrl}/location/${locationId}`);
  }

  getById(id: number): Observable<Prediction> {
    return this.http.get<Prediction>(`${this.apiUrl}/${id}`);
  }
}
