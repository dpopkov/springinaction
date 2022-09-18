package learn.sprng.action6.c05e01security;

import learn.sprng.action6.c05e01security.data.AppUserRepository;
import learn.sprng.action6.c05e01security.data.IngredientRepository;
import learn.sprng.action6.c05e01security.security.RegistrationForm;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class C05e01securityApplication {

    public static void main(String[] args) {
        SpringApplication.run(C05e01securityApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo,
                                        AppUserRepository appUserRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            if (repo.count() == 0) {
                DefaultIngredients.getList().forEach(repo::save);
            }
            RegistrationForm admin = new RegistrationForm();
            admin.setUsername("admin");
            admin.setPassword("admin");
            appUserRepository.save(admin.toAdminAppUser(passwordEncoder));
        };
    }
}
