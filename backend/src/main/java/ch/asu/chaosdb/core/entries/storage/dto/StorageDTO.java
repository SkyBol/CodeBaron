package ch.asu.chaosdb.core.entries.storage.dto;

import ch.asu.chaosdb.core.abstracts.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class StorageDTO extends AbstractDTO {
    private String fileName;
    private String fileType;
    private String originalFileChecksum;
}
