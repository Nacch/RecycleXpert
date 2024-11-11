export interface Volunteer{
    id: number;
    volunteerName: string;
    email: string;
    address: string;
    points: number;
    category: String;
    level: number;
    
    //private Long rangoId;  // Foreign key reference to Rango
    //private Long userId;   // Foreign key reference to User


}
