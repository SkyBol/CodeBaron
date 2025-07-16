package ch.asu.chaosdb.core.entries.access.user;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import ch.asu.chaosdb.core.abstracts.filter.IgnoreFilter;
import ch.asu.chaosdb.core.entries.access.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class User extends AbstractEntity {

  // TODO: Considering E-Mails in the Future
  // Currently, login happens over an username, for added safety of protection of users

  @Column(name = "username")
  private String username;

  @IgnoreFilter
  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_role",
          joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles = new HashSet<>();

}
