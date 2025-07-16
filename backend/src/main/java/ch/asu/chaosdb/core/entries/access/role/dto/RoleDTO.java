package ch.asu.chaosdb.core.entries.access.role.dto;

import ch.asu.chaosdb.core.abstracts.AbstractDTO;
import ch.asu.chaosdb.core.entries.access.authority.dto.AuthorityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class RoleDTO extends AbstractDTO {
  private String name;

  private Set<AuthorityDTO> authorities;
}
