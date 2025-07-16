package ch.asu.chaosdb.domain.chaos.content.types.dto;

import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("TEXT")
@Getter@Setter
public class ChaosTextDTO extends ChaosContentDTO {
    private String title;
    private String textContent;
}
