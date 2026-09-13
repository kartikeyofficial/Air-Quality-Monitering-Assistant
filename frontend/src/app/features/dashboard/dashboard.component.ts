import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthService } from '../../core/services/auth.service';
import { LocationService } from '../../core/services/location.service';
import { AirQualityService } from '../../core/services/air-quality.service';

import { UserResponse } from '../../core/models/auth.model';
import { Location } from '../../core/models/location.model';
import { AirQualityReading } from '../../core/models/air-quality.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent implements OnInit {
  user: UserResponse | null = null;

  locations: Location[] = [];

  selectedLocation: Location | null = null;

  latestReading: AirQualityReading | null = null;

  loading = true;

  constructor(
    private authService: AuthService,
    private locationService: LocationService,
    private airQualityService: AirQualityService,
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();

    this.loadLocations();
  }

  loadLocations(): void {
    this.locationService.getAll().subscribe({
      next: (locations) => {
        this.locations = locations;

        if (locations.length > 0) {
          this.selectLocation(locations[0]);
        } else {
          this.loading = false;
        }
      },

      error: (error) => {
        console.error('Failed to load locations', error);
        this.loading = false;
      },
    });
  }

  selectLocation(location: Location): void {
    this.selectedLocation = location;

    this.loading = true;

    this.airQualityService.getLatest(location.id).subscribe({
      next: (readings) => {
        if (readings.length > 0) {
          this.latestReading = readings[0];
        } else {
          this.latestReading = null;
        }

        this.loading = false;
      },

      error: (error) => {
        console.error('Failed to load air quality', error);

        this.loading = false;
      },
    });
  }

  logout(): void {
    this.authService.logout();
    window.location.href = '/login';
  }
}

