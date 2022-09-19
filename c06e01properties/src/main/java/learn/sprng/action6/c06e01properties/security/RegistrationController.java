package learn.sprng.action6.c06e01properties.security;

import learn.sprng.action6.c06e01properties.AppUser;
import learn.sprng.action6.c06e01properties.data.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        AppUser user = form.toAppUser(passwordEncoder);
        appUserRepository.save(user);
        return "redirect:/login";
    }
}
