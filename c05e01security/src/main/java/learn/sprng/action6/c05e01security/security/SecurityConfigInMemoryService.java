package learn.sprng.action6.c05e01security.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Profile("in-memory-user-details")
@Configuration
public class SecurityConfigInMemoryService {

    private static final String ROLE_USER = "ROLE_USER";

    public SecurityConfigInMemoryService() {
        log.info("Init security config: {}", SecurityConfigInMemoryService.class.getName());
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> users = new ArrayList<>();
        users.add(new User("james", encoder.encode("password"),
                List.of(new SimpleGrantedAuthority(ROLE_USER))));
        users.add(new User("alice", encoder.encode("password"),
                List.of(new SimpleGrantedAuthority(ROLE_USER))));
        return new InMemoryUserDetailsManager(users);
    }

}
