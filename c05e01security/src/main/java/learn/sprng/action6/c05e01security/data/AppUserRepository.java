package learn.sprng.action6.c05e01security.data;

import learn.sprng.action6.c05e01security.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
