import { Component } from '@angular/core';
import { VolunteerService } from '../../services/volunteer.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  volunteerPoints: number = 120; // Estos son puntos actuales del voluntario
  maxPoints: number = 200; // Estos son puntos máximos necesarios para subir al siguiente nivel
  volunteerProgress: unknown;

  constructor(
    private volunteerService: VolunteerService,
  ) {}

  
  ngOnInit() {
 

    
  }
  

  // Función para calcular el progreso en porcentaje
  calculateProgress(points: number): number {
    return (points / this.maxPoints) * 100;
  }
  
  // Función para calcular los puntos faltantes para el siguiente nivel
  calculateRemainingPoints(points: number): number {
    return this.maxPoints - points > 0 ? this.maxPoints - points : 0;
  }



}
