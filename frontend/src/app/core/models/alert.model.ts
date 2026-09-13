export interface Alert {
  id: number;

  userId?: number;
  locationId?: number;

  alertType: string;
  message: string;
  severity: string;

  isRead: boolean;
  createdAt: string;
}
