import { Component, ViewChild, OnInit, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Evento } from '../../models/evento'; // Ajusta la ruta según tu estructura
import { EventService } from '../../services/event.service'; // Servicio de eventos

@Component({
  selector: 'app-event-listar',
  templateUrl: './event-listar.component.html',
  styleUrls: ['./event-listar.component.css']
})
export class EventListarComponent implements OnInit, AfterViewInit {
  cant_Event: number = 0;
  dsEvent = new MatTableDataSource<Evento>();
  displayedColumns: string[] = ['id', 'eventName', 'description', 'date', 'location', 'capacity', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  ngAfterViewInit(): void {
    this.dsEvent.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dsEvent.filter = filterValue.trim().toLowerCase();
  }

  loadEvents() {
    this.eventService.getEvents().subscribe({
      next: (data: Evento[]) => {
        this.dsEvent.data = data;
        this.cant_Event = data.length;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  borrarEvento(id: number) {
    this.eventService.deleteEvent(id).subscribe({
      next: () => {
        this.loadEvents(); // Recargar la lista después de eliminar
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
