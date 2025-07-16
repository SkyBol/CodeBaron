package ch.asu.chaosdb.domain.chaos.content.types.dto;

import ch.asu.chaosdb.core.entries.storage.dto.StorageDTO;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosFile;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("FILE")
@Getter@Setter
public class ChaosFileDTO extends ChaosContentDTO {
    private StorageDTO storage;
    private ChaosFile.FileType fileType;
}
