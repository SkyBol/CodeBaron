package ch.asu.chaosdb.core.entries.storage.unencrypted;

import ch.asu.chaosdb.core.entries.storage.Storage;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter@Setter
@DiscriminatorValue("UNENCRYPTED")
public class UnencryptedFileStorage extends Storage {
}
