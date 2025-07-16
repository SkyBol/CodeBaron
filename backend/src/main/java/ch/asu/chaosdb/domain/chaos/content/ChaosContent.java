package ch.asu.chaosdb.domain.chaos.content;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chaos_content")
@NoArgsConstructor
@Getter@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "mediaType", discriminatorType = DiscriminatorType.STRING)
public class ChaosContent extends AbstractEntity {
    /**
     * Connects all different types by inheritance
     * Below are fields used by all (media) types
     */

    @Column(name = "mediaType", insertable = false, updatable = false)
    private String mediaType;
}
