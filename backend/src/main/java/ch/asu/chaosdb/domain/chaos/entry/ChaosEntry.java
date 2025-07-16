package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import ch.asu.chaosdb.domain.chaos.content.ChaosContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "chaos_entry")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class ChaosEntry extends AbstractEntity {
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "chaos_content_id", referencedColumnName = "id", unique = true, nullable = false)
  private ChaosContent chaosContent;
}
