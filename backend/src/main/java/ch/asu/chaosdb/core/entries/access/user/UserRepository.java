package ch.asu.chaosdb.core.entries.access.user;

import ch.asu.chaosdb.core.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {
  Optional<User> findByUsername(String username);
}
