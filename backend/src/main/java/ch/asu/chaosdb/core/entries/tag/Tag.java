package ch.asu.chaosdb.core.entries.tag;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class Tag extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;
}
