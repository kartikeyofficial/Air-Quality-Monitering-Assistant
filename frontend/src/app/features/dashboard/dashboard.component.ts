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

  loadingLocations = true;
  loadingReading = false;

  errorMessage = '';

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
    this.loadingLocations = true;
    this.errorMessage = '';

    this.locationService.getAll().subscribe({
      next: (locations) => {
        this.locations = locations;

        if (locations.length > 0) {
          this.selectLocation(locations[0]);
        } else {
          this.loadingLocations = false;
          this.errorMessage = 'No locations are available.';
        }
      },

      error: (error) => {
        console.error('Failed to load locations:', error);

        this.loadingLocations = false;

        this.errorMessage = 'Unable to load locations.';
      },
    });
  }

  selectLocation(location: Location): void {
    this.selectedLocation = location;

    this.loadLatestReading(location.id);
  }

  onLocationChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;

    const locationId = Number(selectElement.value);

    const location = this.locations.find((item) => item.id === locationId);

    if (location) {
      this.selectLocation(location);
    }
  }

  loadLatestReading(locationId: number): void {
    this.loadingReading = true;
    this.latestReading = null;

    this.airQualityService.getLatest(locationId).subscribe({
      next: (response) => {
        if (response.content && response.content.length > 0) {
          this.latestReading = response.content[0];
        } else {
          this.latestReading = null;
        }

        this.loadingReading = false;
        this.loadingLocations = false;
      },

      error: (error) => {
        console.error('Failed to load air quality:', error);

        this.loadingReading = false;
        this.loadingLocations = false;

        this.errorMessage = 'Unable to load air quality data.';
      },
    });
  }

  getAqiClass(): string {
    if (!this.latestReading) {
      return '';
    }

    const aqi = this.latestReading.aqi;

    if (aqi <= 50) {
      return 'aqi-good';
    }

    if (aqi <= 100) {
      return 'aqi-satisfactory';
    }

    if (aqi <= 200) {
      return 'aqi-moderate';
    }

    if (aqi <= 300) {
      return 'aqi-poor';
    }

    if (aqi <= 400) {
      return 'aqi-very-poor';
    }

    return 'aqi-severe';
  }

  logout(): void {
    this.authService.logout();

    window.location.href = '/login';
  }
}
