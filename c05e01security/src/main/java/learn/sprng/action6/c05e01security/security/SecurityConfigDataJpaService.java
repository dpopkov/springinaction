package learn.sprng.action6.c05e01security.security;

import learn.sprng.action6.c05e01security.AppUser;
import learn.sprng.action6.c05e01security.Profiles;
import learn.sprng.action6.c05e01security.data.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Slf4j
@Profile(Profiles.USER_DETAILS_DATA_JPA)
@Configuration
public class SecurityConfigDataJpaService {

    public SecurityConfigDataJpaService() {
        log.info("Init security config: {}", SecurityConfigDataJpaService.class.getName());
    }

    @Bean
    public UserDetailsService userDetailsService(AppUserRepository appUserRepository) {
        return username -> {
            log.debug("Getting user: {}", username);
            Optional<AppUser> byUsername = appUserRepository.findByUsername(username);
            if (byUsername.isEmpty()) {
                log.debug("User not found: {}", username);
                throw new UsernameNotFoundException("User '" + username + "' not found");
            }
            log.debug("User found: {}", username);
            return byUsername.get();
        };
    }
}
