package gamifiedproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import gamifiedproject.auth.AuthenticationService;
import gamifiedproject.auth.RegisterRequest;

import  gamifiedproject.Model.entities.Role;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class GamifiedprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamifiedprojectApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			
//			var admin = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.gmail("admin.admin@univ-constantine2.dz")
//					.password("password123$")
//					.role(Role.ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//		};
//	}

}
