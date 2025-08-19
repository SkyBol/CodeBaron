package ch.asu.chaosdb.domain.chaos.entry.dto;

import ch.asu.chaosdb.core.abstracts.AbstractDTO;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter@Setter
public class ChaosEntryDTO extends AbstractDTO {
    private LocalDateTime createdAt;
    private String title;
    private List<ChaosContentDTO> content;
}
