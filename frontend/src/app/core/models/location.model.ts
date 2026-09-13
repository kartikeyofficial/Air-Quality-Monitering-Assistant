export interface Location {
  id: number;
  city: string;
  state: string;
  country: string;
  latitude: number;
  longitude: number;
}

export interface LocationRequest {
  city: string;
  state: string;
  country: string;
  latitude: number;
  longitude: number;
}
