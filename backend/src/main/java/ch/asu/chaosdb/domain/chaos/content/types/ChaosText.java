package ch.asu.chaosdb.domain.chaos.content.types;

import ch.asu.chaosdb.domain.chaos.content.ChaosContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@DiscriminatorValue("TEXT")
public class ChaosText extends ChaosContent {
    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent;
}
