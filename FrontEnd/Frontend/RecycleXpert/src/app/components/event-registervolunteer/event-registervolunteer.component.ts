import { Component } from '@angular/core';
import { EventService } from '../../services/event.service';
import { UserService } from '../../services/user.service';
import { EventvolunteerService } from '../../services/eventvolunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Evento } from '../../models/evento';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-event-registervolunteer',
  templateUrl: './event-registervolunteer.component.html',
  styleUrl: './event-registervolunteer.component.css',
})
export class EventRegistervolunteerComponent {

  selectedEvents: number[] = []; 
  cant_Event: number = 0; 
  eventsList = new MatTableDataSource<Evento>(); 
  selectedEvent: number | null = null; 
  displayedColumns: string[] = ['eventName', 'date', 'description', 'location', 'select']; // Columnas de la tabla

  constructor(
    private eventService: EventService,
    private eventvolunteerService: EventvolunteerService,
    private userService: UserService,
    private snack: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEvents(); 
  }

  loadEvents() {
    this.eventService.getEvents().subscribe({
      next: (data: Evento[]) => {
        this.eventsList.data = data;
        this.cant_Event = data.length;
      },
      error: (err) => {
        console.error('Error al cargar eventos:', err);
      },
    });
  }

  // Registrar al voluntario en los eventos seleccionados
  registerForEvent() {
    if (this.selectedEvents.length === 0) {
      this.snack.open('Por favor, selecciona al menos un evento', 'OK', { duration: 2000 });
      return;
    }

    const volunteerId = this.userService.getUserIdActual();
    
    if (volunteerId === null) {
      this.snack.open('Error: No se pudo obtener el ID del voluntario. Por favor, inicia sesión.', 'OK', { duration: 2000 });
      return;
    }

    // Registrar al voluntario en cada evento seleccionado
    const registerRequests = this.selectedEvents.map((eventId) =>
      this.eventvolunteerService.registerVolunteerToEvent(+volunteerId, eventId).toPromise()
    );

    Promise.all(registerRequests)
      .then(() => {
        this.snack.open('Te has registrado exitosamente a los eventos seleccionados', 'OK', { duration: 2000 });
        this.router.navigate(['/home']);
      })
      .catch(() => {
        this.snack.open('Error al registrarte en uno o más eventos', 'OK', { duration: 2000 });
      });
  }

  // Alternar selección de eventos
  toggleEventSelection(eventId: number) {
    const index = this.selectedEvents.indexOf(eventId);

    if (index > -1) {

      this.selectedEvents.splice(index, 1);
    } else if (this.selectedEvents.length < 2) {

      this.selectedEvents.push(eventId);
    } else {
      this.snack.open('Solo puedes seleccionar hasta 2 eventos', 'OK', { duration: 2000 });
    }
  }

  // Cancelar y regresar
  cancel() {
    this.router.navigate(['/home']);
  }

  // Seleccionar un evento
  selectEvent(eventId: number) {
    this.selectedEvent = eventId;
  }
}
