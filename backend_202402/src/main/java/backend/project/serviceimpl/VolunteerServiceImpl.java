package backend.project.serviceimpl;

import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Consultation;
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
        for (Volunteer v : volunteerList) {
            for(Consultation u: v.getConsultations()){
                u.setVolunteer(null);
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
        Volunteer volunteerFound = findById(id);
        if(volunteerFound != null){
            if(volunteerdto.getVolunteerName() != null){
                if (!isNameDuplicated(volunteerdto.getVolunteerName()) ) {
                    volunteerFound.setVolunteerName(volunteerdto.getVolunteerName());
                } else {
                    throw new KeyRepeatedDataException("Volunteer name:"+ volunteerdto.getVolunteerName()+" can not be duplicated");
                }

            }
            if(volunteerdto.getEmail() != null){
                volunteerFound.setEmail(volunteerdto.getEmail());
            }
            if(volunteerdto.getAddress() != null){
                volunteerFound.setAddress(volunteerdto.getAddress());
            }
            return volunteerRepository.save(volunteerFound);
        }
        return null;
    }
}
