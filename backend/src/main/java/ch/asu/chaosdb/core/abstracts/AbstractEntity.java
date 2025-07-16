package ch.asu.chaosdb.core.abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
  private UUID id;

}
