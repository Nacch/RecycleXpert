import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Evento } from '../../models/evento';
import { EventType } from '../../models/eventType';
import { EventTypeService } from '../../services/event-type.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-event-register',
  templateUrl: './event-register.component.html',
  styleUrl: './event-register.component.css'
})
export class EventRegisterComponent {
  addFormEvent! : FormGroup;

  eventType!: EventType[];
  eventId:number=0;


  constructor(
    private eventService : EventService,
    private eventTypeService: EventTypeService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private datePipe: DatePipe  // Inyecta DatePipe

  ){}

  ngOnInit(): void {
    this.CrearFormulario();
    this.cargarTiposDeEventos();

    this.eventId = parseInt(this.activatedRoute.snapshot.params['id'], 10);
    if (this.eventId) {
      this.cargarEventoParaEdicion(this.eventId);
    }

  }
  cargarEventoParaEdicion(id: number): void {
    this.eventService.getEvent(id).subscribe({
      next: (dataEvent: Evento) => {
        this.addFormEvent.patchValue(dataEvent);
      },
      error: (err) => {
        console.error('Error al cargar datos del evento', err);
        this.snackBar.open('Error al cargar datos del evento', 'Ok', { duration: 3000 });
      }
    });
  }
  cargarTiposDeEventos(): void {
    this.eventTypeService.getEventTypes().subscribe({
      next:(data: EventType[]) => {
        this.eventType = data;
      },
      error: (err) => {
        console.error("Error al cargar tipos de eventos", err);
        this.snackBar.open("Error al cargar tipos de eventos", "Ok", { duration: 3000 });
      }
    });
  }

  CrearFormulario(){
    this.addFormEvent = this.fb.group({
      id:[""],
      eventName: ['', Validators.required],
      description: ['', Validators.required],
      date: ['', Validators.required],
      location: ['', Validators.required],
      capacity: [1, [Validators.required, Validators.min(1)]],
      organizationId: [''],
      eventTypeId: ['']
    });

    this.eventId = parseInt(this.activatedRoute.snapshot.params["id"]);

    if (this.eventId > 0 && this.eventId != undefined) {
        // Cargar los datos para edición
        this.eventService.getEvent(this.eventId).subscribe({
            next: (dataEvent: Evento) => { 
                // Asignar valores al formulario
                this.addFormEvent.patchValue({
                    eventName: dataEvent.eventName,
                    description: dataEvent.description,
                    date: dataEvent.date,
                    location: dataEvent.location,
                    capacity: dataEvent.capacity,
                    organizationId: dataEvent.organizationId,
                    eventTypeId: dataEvent.eventTypeId
                });

                // Obtener tipos de eventos (si usas un dropdown o lista)
                this.eventTypeService.getEventTypes().subscribe({
                    next: (dataEventTypes: EventType[]) => {
                        this.eventType = dataEventTypes;
                        // Aquí puedes establecer el `eventTypeId` en el formulario si es necesario
                    }
                });
            }
        });
    } else {
        // Si es un nuevo evento, podrías cargar la lista de tipos de eventos también aquí
        this.eventTypeService.getEventTypes().subscribe({
            next: (dataEventTypes: EventType[]) => {
                this.eventType = dataEventTypes;
            }
        });
    }

  }

  RegistrarEvento(){
    if (this.addFormEvent.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }

    // Convierte la fecha al formato deseado (por ejemplo, 'yyyy-MM-dd')
    const date = this.datePipe.transform(this.addFormEvent.get("date")?.value, 'yyyy-MM-dd');


    const evento: Evento = {
      id: 0, 
      eventName: this.addFormEvent.get("eventName")?.value,
      description: this.addFormEvent.get("description")?.value,
      date: date!,
      location: this.addFormEvent.get("location")?.value,
      capacity: this.addFormEvent.get('capacity')?.value,
      organizationId: this.addFormEvent.get('organizationId')?.value,
      eventTypeId: this.addFormEvent.get('eventTypeId')?.value
    };

       // Convierte la fecha al formato deseado (por ejemplo, 'yyyy-MM-dd')

    console.log("Evento a registrar:", evento);  // Verificar valores del registro

    this.eventService.addEvent(evento).subscribe({
        next: (data) => {
            this.snackBar.open("Evento registrado correctamente", "Ok", { duration: 3000 });
            this.router.navigate(['/volunteer-listar']);
        },
        error: (err) => {
            this.snackBar.open("Error al registrar el evento", "Ok", { duration: 3000 });
        }
    });

  }

  onCancel(): void {
    this.router.navigate(['/volunteer-listar']);
  }

}
