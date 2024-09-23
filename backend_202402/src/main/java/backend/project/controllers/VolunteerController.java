package backend.project.controllers;

import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Volunteer;
import backend.project.services.UserService;
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
}
