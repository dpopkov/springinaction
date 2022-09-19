package learn.sprng.action6.c06e01properties;

import learn.sprng.action6.c06e01properties.data.AppUserRepository;
import learn.sprng.action6.c06e01properties.data.IngredientRepository;
import learn.sprng.action6.c06e01properties.security.RegistrationForm;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class C06e01propertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(C06e01propertiesApplication.class, args);
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
