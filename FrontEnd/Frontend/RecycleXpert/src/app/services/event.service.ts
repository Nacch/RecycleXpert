import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Evento } from '../models/evento';

@Injectable({
  providedIn: 'root'
})
export class EventService {

    // Ruta a donde se conectará
    servidor: string = "http://localhost:8080/api";
    recurso: string = "events";
  
  constructor(private http: HttpClient) { }
     //Para mostar en Lista o Listar
  getEvents(){
    return this.http.get<Evento[]>(this.servidor+"/"+this.recurso);
  }
  // Para buscar en el registrar y pueda verificar el editar
  getEvent(id: number){
    return this.http.get<Evento>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }

  //Para eliminar
  deleteEvent(id:number){
    return this.http.delete<Evento>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }
  //Para añadir o insertar
  addEvento(evento : Evento){
    return this.http.post<Evento>(this.servidor+"/"+this.recurso, evento);
  }

  //Para el llamado del editar
  editEvent(evento: Evento){
    return this.http.put<Evento>(this.servidor+"/"+this.recurso+"/"+evento.id.toString(),evento);
  }
}
