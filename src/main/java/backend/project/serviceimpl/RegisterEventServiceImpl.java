package backend.project.serviceimpl;

import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;
import backend.project.entities.Volunteer;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventRepository;
import backend.project.repositories.RegisterEventRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.RegisterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterEventServiceImpl implements RegisterEventService {
    @Autowired
    private RegisterEventRepository registerEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Agregar inscripción de un voluntario a un evento
    public RegisterEvent registerVolunteerToEvent(Long eventId, Long volunteerId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<Volunteer> volunteerOpt = volunteerRepository.findById(volunteerId);

        if (eventOpt.isPresent() && volunteerOpt.isPresent()) {
            Event event = eventOpt.get();
            Volunteer volunteer = volunteerOpt.get();

            RegisterEvent registerEvent = new RegisterEvent();
            registerEvent.setEvent(event);
            registerEvent.setVolunteer(volunteer);
            registerEvent.setAttendance(false); // Por defecto no ha asistido

            return registerEventRepository.save(registerEvent);
        } else {
            throw new ResourceNotFoundException("Event or Volunteer not found");
        }
    }

    // Eliminar inscripción de un voluntario de un evento
    public void unregisterVolunteerFromEvent(Long eventId, Long volunteerId) {
        Optional<RegisterEvent> registerEventOpt = registerEventRepository.findRegisterEventByEvent_IdAndVolunteerId(eventId, volunteerId);

        if (registerEventOpt.isPresent()) {
            registerEventRepository.delete(registerEventOpt.get());
        } else {
            throw new ResourceNotFoundException("No registration found for the given event and volunteer");
        }
    }
}
