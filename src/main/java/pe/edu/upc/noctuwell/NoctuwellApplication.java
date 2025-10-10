package pe.edu.upc.noctuwell;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.noctuwell.entities.Authority;
import pe.edu.upc.noctuwell.entities.User;
import pe.edu.upc.noctuwell.repositories.PatientRepository;
import pe.edu.upc.noctuwell.repositories.SpecialistRepository;
import pe.edu.upc.noctuwell.services.AuthorityService;
import pe.edu.upc.noctuwell.services.UserService;

import java.util.List;

@SpringBootApplication
public class NoctuwellApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoctuwellApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
            UserService userService,
            AuthorityService authorityService,
            PatientRepository patientRepository,
            SpecialistRepository specialistRepository

    ) {
        return args -> {

            Authority authority1 = authorityService.addAuthority(new Authority("ROLE_ADMIN"));
            Authority authority2 = authorityService.addAuthority(new Authority("ROLE_SPECIALIST"));
            Authority authority3 = authorityService.addAuthority(new Authority("ROLE_PATIENT"));

            User u1 = userService.addUser(new User(null, "emma", "pipoishere?", true,
                    List.of(authority1, authority2)));

            User u2 = userService.addUser(new User(null, "esp1", "esp1", true,
                    List.of(authority2)));

            User u3 = userService.addUser(new User(null, "esp2", "esp2", true,
                    List.of(authority2)));

            User u4 = userService.addUser(new User(null, "paciente1", "paciente1", true,
                    List.of(authority3)));

            User u5 = userService.addUser(new User(null, "paciente2", "paciente2", true,
                    List.of(authority3)));

        };
    }
}
