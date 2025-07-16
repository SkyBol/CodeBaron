package ch.asu.chaosdb.core.entries.access.authority.dto;

import ch.asu.chaosdb.core.abstracts.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class AuthorityDTO extends AbstractDTO  {
  private String name;
}
