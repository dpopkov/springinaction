package learn.sprng.action6.c06e01properties.data;

import learn.sprng.action6.c06e01properties.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
