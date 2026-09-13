export interface AirQualityReading {
  id: number;
  locationId?: number;

  pm25: number;
  pm10: number;
  co: number;
  no2: number;
  so2: number;
  o3: number;

  temperature: number;
  humidity: number;
  windSpeed: number;
  pressure: number;

  aqi: number;
  aqiCategory: string;

  recordedAt: string;
}
