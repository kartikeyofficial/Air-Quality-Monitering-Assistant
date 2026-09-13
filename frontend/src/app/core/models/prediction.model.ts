export interface Prediction {
  id: number;
  locationId: number;

  predictedPm25: number;
  predictedAqi: number;
  predictedCategory: string;

  predictionFor: string;
  createdAt: string;
}
