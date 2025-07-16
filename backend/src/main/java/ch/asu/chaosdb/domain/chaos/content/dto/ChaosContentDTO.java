package ch.asu.chaosdb.domain.chaos.content.dto;

import ch.asu.chaosdb.core.abstracts.AbstractDTO;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosFileDTO;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "mediaType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChaosTextDTO.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ChaosFileDTO.class, name = "FILE")
})
@Getter@Setter
public class ChaosContentDTO extends AbstractDTO {
    private String mediaType;
}
