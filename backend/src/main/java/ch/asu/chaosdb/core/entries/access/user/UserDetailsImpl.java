package ch.asu.chaosdb.core.entries.access.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record UserDetailsImpl(User user) implements UserDetails {
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null; // TODO
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}
