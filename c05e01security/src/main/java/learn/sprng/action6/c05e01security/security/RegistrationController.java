package learn.sprng.action6.c05e01security.security;

import learn.sprng.action6.c05e01security.AppUser;
import learn.sprng.action6.c05e01security.Profiles;
import learn.sprng.action6.c05e01security.data.AppUserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile(Profiles.USER_DETAILS_DATA_JPA)
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
