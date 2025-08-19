package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import ch.asu.chaosdb.core.entries.tag.Tag;
import ch.asu.chaosdb.domain.chaos.content.ChaosContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chaos_entry")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class ChaosEntry extends AbstractEntity {
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "title")
  private String title;

  @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChaosContent> content = new ArrayList<>();

  @ManyToMany
  @JoinTable(
          name = "chaos_entry_tag",
          joinColumns = @JoinColumn(name = "entry_id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags = new HashSet<>();
}
