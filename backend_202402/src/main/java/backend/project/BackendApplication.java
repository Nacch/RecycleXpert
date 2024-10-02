package backend.project;

import backend.project.dtos.UserDTO;
import backend.project.entities.Authority;
import backend.project.entities.User;
import backend.project.repositories.AuthorityRepository;
import backend.project.repositories.OrganizationRepository;
import backend.project.repositories.UserRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.AuthorityService;
import backend.project.services.UserService;
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
		AuthorityService authorityService

	) {
		return args -> {
			authorityService.addAuthority(new Authority(0L,"REGISTRO",null));
			authorityService.addAuthority(new Authority(0L,"CONSULTA",null));

			// Crear un usuario
			userService.addUser(new UserDTO(0L,"Frank","123","REGISTRO"));
			userService.addUser(new UserDTO(0L,"Luis","321","CONSULTA"));
			authorityService.addAuthority(new Authority(0L,"ADMINISTRADOR",null));


		};


	}

}
