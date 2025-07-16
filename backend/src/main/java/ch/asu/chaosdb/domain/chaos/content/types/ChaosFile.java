package ch.asu.chaosdb.domain.chaos.content.types;

import ch.asu.chaosdb.core.entries.storage.Storage;
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
@DiscriminatorValue("FILE")
public class ChaosFile extends ChaosContent {
    /**
     * The actual files
     * Is either encrypted or unencrypted. If both, something's not good
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_storage_id", referencedColumnName = "id")
    private Storage fileStorage;

    @Enumerated(EnumType.ORDINAL) // Expansion not allowed/Difficult with ORDINAL
    private FileType fileType;

    public enum FileType {
        IMAGE, AUDIO, VIDEO, DOCUMENT;
    }
}
