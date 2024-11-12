import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Evento } from '../../models/evento';

@Component({
  selector: 'app-event-register',
  templateUrl: './event-register.component.html',
  styleUrl: './event-register.component.css'
})
export class EventRegisterComponent {
  addFormEvent! : FormGroup;

  constructor(
    private eventService : EventService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute
  ){}

  ngOnInit(): void {
    this.CrearFormulario();
  }

  CrearFormulario(){
    this.addFormEvent = this.fb.group({
      eventName: ['', Validators.required],
      description: ['', Validators.required],
      date: ['', Validators.required],
      location: ['', Validators.required],
      capacity: [1, [Validators.required, Validators.min(1)]],
      organizationId: [''],
      eventTypeId: ['']
    });
  }

  RegistrarEvento(){
    if (this.addFormEvent.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }

    const evento: Evento = {
      id: 0, 
      eventName: this.addFormEvent.get("eventName")?.value,
      description: this.addFormEvent.get("description")?.value,
      date: this.addFormEvent.get("date")?.value,
      location: this.addFormEvent.get("location")?.value,
      capacity: this.addFormEvent.get("capacity")?.value,
      //organizationId: this.addFormEvent.get("organizationId")?.value,
      //eventTypeId: this.addFormEvent.get("eventTypeId")?.value
    };

    this.eventService.addEvent(evento).subscribe({
      next: (data) => {
        this.snackBar.open("Evento registrado correctamente", "Ok", { duration: 3000 });
        this.router.navigate(['/event-list']);
      },
      error: (err) => {
        this.snackBar.open("Error al registrar el evento", "Ok", { duration: 3000 });
      }
    });

  }

  onCancel(): void {
    this.router.navigate(['/event-list']);
  }

}
