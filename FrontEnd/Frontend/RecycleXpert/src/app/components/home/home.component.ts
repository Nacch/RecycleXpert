import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

    // Datos ficticios de ejemplo para el voluntario
    volunteerPoints: number = 120; // Puntos actuales del voluntario
    maxPoints: number = 200; // Puntos máximos necesarios para subir al siguiente nivel
  
    // Función para calcular el progreso en porcentaje
    calculateProgress(points: number): number {
      return (points / this.maxPoints) * 100;
    }
  
    // Función para calcular los puntos faltantes para el siguiente nivel
    calculateRemainingPoints(points: number): number {
      return this.maxPoints - points;
    }

}
