package ch.asu.chaosdb.core.entries.access.user;

import ch.asu.chaosdb.core.abstracts.AbstractService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, AbstractService<User> {
}
