package ch.asu.chaosdb.core.entries.access.authority;

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
@Table(name = "authority")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class Authority extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
