import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventTypeService {
  // Ruta a donde se conectará
  servidor: string = "http://localhost:8080/api";
  recurso: string = "eventType";



  constructor() { }
}
