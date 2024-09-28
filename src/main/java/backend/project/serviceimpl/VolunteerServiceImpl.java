package backend.project.serviceimpl;

import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Consultation;
import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;
import backend.project.entities.User;
import backend.project.entities.Volunteer;
import backend.project.exceptions.InvalidActionException;
import backend.project.exceptions.KeyRepeatedDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.UserRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public boolean isNameDuplicated(String volunteerName){
        List<Volunteer> listNombreDuplicados = volunteerRepository.findByVolunteerName(volunteerName);
        return (listNombreDuplicados.size()>0);
    }

    //Modificar esta parte para que Voluntarios liste viendo si tiene uuario
    @Override
    public List<Volunteer> listAllVolunteers() {
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        // Iteramos sobre la lista de voluntarios para revisar la relación con el usuario
        for (Volunteer v : volunteerList) {
            // Verificamos si el voluntario tiene un usuario asociado
            if (v.getUser() != null) {
                for (Consultation u : v.getConsultations()) {
                    u.setVolunteer(null);
                }
            } else {
                // Si el voluntario no tiene un usuario, puedes ignorarlo o hacer otra acción
                // Aquí, solo ignoramos el voluntario sin usuario
                volunteerList.remove(v);  // Puedes omitir esta línea si no deseas eliminar voluntarios sin usuario
            }
        }
        return volunteerList;
    }

    // .-...
    @Override
    public Volunteer addVolunteer(VolunteerDTO volunteerdto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerName(volunteerdto.getVolunteerName());
        volunteer.setEmail(volunteerdto.getEmail());
        volunteer.setAddress(volunteerdto.getAddress());

        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer findById(Long id){
        Volunteer volunteerFound = volunteerRepository.findById(id).orElse(null);
        if(volunteerFound == null){
            throw new ResourceNotFoundException("Volunteer with id: " + id + " not found");
        }
        return volunteerFound;
    }

    @Override
    public void deleteVolunteer(Long id){
        Volunteer volunteer = findById(id);
        if(volunteer != null){
            if(volunteer.getUser().isActive()){
                volunteerRepository.delete(volunteer);
            }
            else{
                throw new InvalidActionException("Volunteer with id: " + id + " can not be deleted because it has FK dependencies");
            }
        }
    }
    @Override
    public Volunteer updateVolunteer(Long id, VolunteerDTO volunteerdto) {
        Volunteer volunteerFound = findById(id); // Busca el voluntario por ID, o lanza una excepción si no lo encuentra.

        // Actualizar el nombre si no es nulo y no está duplicado
        if(volunteerdto.getVolunteerName() != null && !volunteerdto.getVolunteerName().equals(volunteerFound.getVolunteerName())) {
            if (!isNameDuplicated(volunteerdto.getVolunteerName())) {
                volunteerFound.setVolunteerName(volunteerdto.getVolunteerName());
            } else {
                throw new KeyRepeatedDataException("Volunteer name: " + volunteerdto.getVolunteerName() + " cannot be duplicated.");
            }
        }

        // Actualizar el email si no es nulo
        if(volunteerdto.getEmail() != null && !volunteerdto.getEmail().equals(volunteerFound.getEmail())) {
            volunteerFound.setEmail(volunteerdto.getEmail());
        }

        // Actualizar la dirección si no es nula
        if(volunteerdto.getAddress() != null && !volunteerdto.getAddress().equals(volunteerFound.getAddress())) {
            volunteerFound.setAddress(volunteerdto.getAddress());
        }

        // Guardar los cambios en la base de datos
        return volunteerRepository.save(volunteerFound);
    }
    public List<Event> getVolunteerRegisteredEvents(Long volunteerId) {
        // Buscamos el voluntario por su ID
        Volunteer volunteer = findById(volunteerId);

        // Verificamos que el voluntario tenga eventos registrados
        if (volunteer.getRegisterEvents() != null && !volunteer.getRegisterEvents().isEmpty()) {
            // Mapeamos los eventos de los registros de eventos
            return volunteer.getRegisterEvents()
                    .stream()
                    .map(RegisterEvent::getEvent)
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("No registered events found for volunteer with id: " + volunteerId);
        }
    }
}
