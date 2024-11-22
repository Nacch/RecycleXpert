import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Evento } from '../../models/evento';
import { EventTypeService } from '../../services/event-type.service';
import { EventType } from '../../models/eventType';
import { OrganizationService } from '../../services/organization.service';
import { Organization } from '../../models/organization';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditEventComponent implements OnInit {
  editFormEvent!: FormGroup;
  eventId!: number;
  eventType!: EventType[];
  organization!: Organization[];

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private eventTypeService: EventTypeService,
    private organizationService: OrganizationService,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.createForm();
    this.loadEventTypes();
    this.loadOrganizations();

    this.eventId = parseInt(this.activatedRoute.snapshot.params['id'], 10);
    if (this.eventId) {
      this.loadEventData(this.eventId);
    }
  }

  // Crear el formulario
  createForm(): void {
    this.editFormEvent = this.fb.group({
      id: [''],
      eventName: ['', Validators.required],
      description: ['', Validators.required],
      date: ['', Validators.required],
      location: ['', Validators.required],
      capacity: [1, [Validators.required, Validators.min(1)]],
      organizationId: [''],
      eventTypeId: ['']
    });
  }

  // Cargar tipos de eventos
  loadEventTypes(): void {
    this.eventTypeService.getEventTypes().subscribe({
      next: (data: EventType[]) => {
        this.eventType = data;
      },
      error: (err) => {
        this.snackBar.open("Error al cargar tipos de eventos", "Ok", { duration: 3000 });
        console.error("Error al cargar tipos de eventos", err);
      }
    });
  }

  // Cargar organizaciones
  loadOrganizations(): void {
    this.organizationService.getOrganizations().subscribe({
      next: (data: Organization[]) => {
        this.organization = data;
      },
      error: (err) => {
        this.snackBar.open("Error al cargar organizaciones", "Ok", { duration: 3000 });
        console.error("Error al cargar organizaciones", err);
      }
    });
  }

  // Cargar datos del evento para edición
  loadEventData(id: number): void {
    this.eventService.getEvent(id).subscribe({
      next: (event: Evento) => {
        this.editFormEvent.patchValue(event);
        this.eventTypeService.getEventType(event.eventTypeId).subscribe({
          next: (type) => {
            this.eventType = [type];
          }
        });
        this.organizationService.getOrganization(event.organizationId).subscribe({
          next: (org) => {
            this.organization = [org];
          }
        });
      },
      error: (err) => {
        this.snackBar.open("Error al cargar datos del evento", "Ok", { duration: 3000 });
        console.error("Error al cargar datos del evento", err);
      }
    });
  }

  // Actualizar evento
  updateEvent(): void {
    if (this.editFormEvent.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }

    const updatedEvent: Evento = {
      id: this.eventId,
      eventName: this.editFormEvent.get('eventName')?.value,
      description: this.editFormEvent.get('description')?.value,
      date: this.editFormEvent.get('date')?.value,
      location: this.editFormEvent.get('location')?.value,
      capacity: this.editFormEvent.get('capacity')?.value,
      organizationId: this.editFormEvent.get('organizationId')?.value,
      eventTypeId: this.editFormEvent.get('eventTypeId')?.value
    };

    this.eventService.editEvent(updatedEvent).subscribe({
      next: () => {
        this.router.navigate(['/event-listar']);
        this.snackBar.open("Evento actualizado correctamente", "Ok", { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open("Error al actualizar el evento", "Ok", { duration: 3000 });
        console.error("Error al actualizar el evento", err);
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/event-listar']);
  }
}
