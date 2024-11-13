package backend.project;

import backend.project.dtos.EventTypeDTO;
import backend.project.dtos.UserDTO;
import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Authority;
import backend.project.entities.EventType;
import backend.project.entities.User;
import backend.project.repositories.AuthorityRepository;
import backend.project.repositories.OrganizationRepository;
import backend.project.repositories.UserRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.AuthorityService;
import backend.project.services.EventTypeService;
import backend.project.services.UserService;
import backend.project.services.VolunteerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
		VolunteerRepository volunteerRepository,
		UserRepository userRepository,
		OrganizationRepository organizationRepository,
		UserService userService,
		AuthorityService authorityService,
		VolunteerService volunteerService,
		EventTypeService eventTypeService

	) {
		return args -> {
			authorityService.addAuthority(new Authority(0L,"REGISTRO",null));
			authorityService.addAuthority(new Authority(0L,"CONSULTA",null));

			// Crear un usuario
			userService.addUser(new UserDTO(0L,"Frank","123","REGISTRO"));
			userService.addUser(new UserDTO(0L,"Luis","321","CONSULTA"));
			authorityService.addAuthority(new Authority(0L,"ADMINISTRADOR",null));

			// Crear voluntarios de prueba
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Ana Pérez", "ana@example.com", "Av. Siempre Viva 123", 50, "Reciclaje", 1, 1L, 1L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Carlos López", "carlos@example.com", "Calle Falsa 456", 30, "Reciclaje", 2, 2L, 2L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "María Gómez", "maria@example.com", "Calle Real 789", 80, "Educación", 1, 3L, 3L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Moises Martínez", "pedro@example.com", "Calle Luna 101", 100, "Salud", 3, 4L, 4L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Luis Cortez", "pedro@example.com", "Calle Luna 101", 100, "Salud", 3, 4L, 4L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Juan benites", "pedro@example.com", "Calle Luna 101", 100, "Salud", 3, 4L, 4L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Pedro Martínez", "pedro@example.com", "Calle Luna 101", 100, "Salud", 3, 4L, 4L));


			// Crear tipos de eventos
			eventTypeService.addEventType(new EventType(0L, "Reciclaje", "Evento de reciclaje comunitario", 10, null));
			eventTypeService.addEventType(new EventType(0L, "Educación", "Charla educativa", 20, null));
			eventTypeService.addEventType(new EventType(0L, "Salud", "Campaña de salud", 15, null));

			System.out.println("Tipos de eventos creados con éxito.");
			System.out.println("Voluntarios de prueba creados con éxito.");
		};


	}

}
