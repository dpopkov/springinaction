package learn.sprng.action6.c05e01security.security;

import learn.sprng.action6.c05e01security.AppUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;

    public AppUser toAppUser(PasswordEncoder passwordEncoder) {
        return new AppUser(null, username, passwordEncoder.encode(password), SecurityConstants.ROLE_USER);
    }

    public AppUser toAdminAppUser(PasswordEncoder passwordEncoder) {
        return new AppUser(null, username, passwordEncoder.encode(password), SecurityConstants.ROLE_ADMIN);
    }
}
