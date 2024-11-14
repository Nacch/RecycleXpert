package backend.project.serviceimpl;

import backend.project.dtos.EventDTO;
import backend.project.entities.Event;
import backend.project.entities.EventType;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventRepository;
import backend.project.repositories.EventTypeRepository;
import backend.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;
    private Long id;

    private String eventName;
    private String description;
    private Date date;
    private String location;
    private int capacity;

    // Crear un nuevo evento
    public Event createEvent(Event event) {
        try {
            EventType eventType = eventTypeRepository.findById(event.getEventType().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("EventType with id: " + event.getEventType().getId() + " not found"));
            event.setEventType(eventType);
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime detalles del error en la consola
            throw new RuntimeException("Error al crear el evento: " + e.getMessage()); // Propaga el error con detalles adicionales
        }
    }

    // Modificar un evento existente
    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setCapacity(updatedEvent.getCapacity());
            existingEvent.setOrganization(updatedEvent.getOrganization());
            //existingEvent.setEventType(updatedEvent.getEventType());
            // Si es necesario, se actualiza las relaciones como registerEvents, questionEvents y donations
            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Event with id " + id + " not found");
        }
    }

    // Eliminar un evento
    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new RuntimeException("Event with id " + id + " not found");
        }
    }

    // Obtener un evento por su ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with id " + id + " not found"));
    }

    // Obtener todos los eventos
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
