package backend.project.controllers;

import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Event;
import backend.project.entities.Volunteer;
import backend.project.exceptions.KeyRepeatedDataException;
import backend.project.exceptions.ResourceNotFoundException;
//import backend.project.services.UserService;
import backend.project.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
// "http://localhost:8080/api"
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    //HTTP: GET, POST, PUT, DELETE
    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> listAllVolunteer() {
        return new ResponseEntity<List<Volunteer>>(volunteerService.listAllVolunteers(),HttpStatus.OK);
    }
    // Registrar un nuevo voluntario
    @PostMapping("/volunteers/register")
    public ResponseEntity<Volunteer> insertVolunteer(@RequestBody VolunteerDTO volunteerdto) {
        Volunteer newVolunteer = volunteerService.addVolunteer(volunteerdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVolunteer);
    }

    // Obtener un voluntario por ID
    @GetMapping("/volunteers/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        Volunteer volunteerFound = volunteerService.findById(id);
        if (volunteerFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(volunteerFound);
    }

    // Eliminar un voluntario por ID
    @DeleteMapping("/volunteers/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable("id") Long id) {
        volunteerService.deleteVolunteer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Listar todos los eventos a los que un voluntario está inscrito
    @GetMapping("/listEventsperVolunteer/{volunteerId}/events")
    public ResponseEntity<List<Event>> getVolunteerRegisteredEvents(@PathVariable Long volunteerId) {
        try {
            List<Event> events = volunteerService.getVolunteerRegisteredEvents(volunteerId);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Actualizar un voluntario existente
    @PutMapping("/updateVolunteers/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody VolunteerDTO volunteerdto) {
        try {
            Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteerdto);
            return new ResponseEntity<>(updatedVolunteer, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (KeyRepeatedDataException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
