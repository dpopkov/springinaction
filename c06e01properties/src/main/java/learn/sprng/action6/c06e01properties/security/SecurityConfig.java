package learn.sprng.action6.c06e01properties.security;

import learn.sprng.action6.c06e01properties.AppUser;
import learn.sprng.action6.c06e01properties.data.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String H_2_CONSOLE = "/h2-console/**";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    // Can ignore 'Could not autowire'
        return http
                .authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/design", "/orders").access("hasAnyRole('USER', 'ADMIN')")
                    .antMatchers("/", "/**", H_2_CONSOLE).access("permitAll()")

                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")

                // H2-Console non-secured for debug purposes
                .and()
                    .csrf().ignoringAntMatchers(H_2_CONSOLE)

                // Allow pages to be loaded in frames from the same origin. Needed for H2-Console.
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .build();
    }

}
