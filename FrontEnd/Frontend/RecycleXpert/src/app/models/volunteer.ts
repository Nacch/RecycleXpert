export interface Volunteer{
    id: number;
    volunteerName: string;
    email: string;
    address: string;
    points: number;
    category: String;
    level: number;
    rangoId?: number;  // Optional
    userId?: number;   // Optional
}
