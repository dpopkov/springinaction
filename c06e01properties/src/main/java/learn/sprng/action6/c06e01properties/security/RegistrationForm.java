package learn.sprng.action6.c06e01properties.security;

import learn.sprng.action6.c06e01properties.AppUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String street;
    private String city;
    private String state;
    private String zip;

    public AppUser toAppUser(PasswordEncoder passwordEncoder) {
        return new AppUser(null, username, passwordEncoder.encode(password), SecurityConstants.ROLE_USER,
                street, city, state, zip);
    }

    public AppUser toAdminAppUser(PasswordEncoder passwordEncoder) {
        return new AppUser(null, username, passwordEncoder.encode(password), SecurityConstants.ROLE_ADMIN,
                null, null, null, null);
    }
}
