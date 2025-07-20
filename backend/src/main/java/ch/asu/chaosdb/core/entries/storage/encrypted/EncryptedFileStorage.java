package ch.asu.chaosdb.core.entries.storage.encrypted;

import ch.asu.chaosdb.core.entries.storage.Storage;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@DiscriminatorValue("ENCRYPTED")
public class EncryptedFileStorage extends Storage {
    // A generated random key from other props, without salt/pepper
    @Column(name = "encryptionKey", nullable = false)
    private String key;

    @Column(name = "salt", nullable = false, unique = true)
    private String salt;

    // If this is null, the file isn't saved on the server
    @Column(name = "encrypted_file_checksum", nullable = true)
    private String encryptedFileChecksum;
}
