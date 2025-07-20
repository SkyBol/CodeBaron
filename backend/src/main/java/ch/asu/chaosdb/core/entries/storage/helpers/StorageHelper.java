package ch.asu.chaosdb.core.entries.storage.helpers;

import ch.asu.chaosdb.core.entries.storage.StorageConfig;
import ch.asu.chaosdb.core.exception.customexceptions.StorageException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Getter@Setter
public class StorageHelper {
    private final StorageConfig config;

    public StorageHelper(StorageConfig config) {
        this.config = config;
    }

    public Path generateFileStoragePath(String storageId, StorageDepth storageDepth) throws IOException {
        // Get path to storage
        Path location = Path.of(this.config.getLocation()).toAbsolutePath();
        createIfNotExists(location);

        // Add specialized Depth if needed
        if (!storageDepth.equals(StorageDepth.NONE)) {
            location = location.resolve(Paths.get(getConfigStorageDepth(storageDepth)))
                .normalize().toAbsolutePath();
        }
        createIfNotExists(location);

        // Get file to ID
        Path file = location
                .resolve(Paths.get(storageId))
                .normalize().toAbsolutePath();

        // Check if file would get saved outside directory
        if (!file.getParent().equals(location)) {
            throw new StorageException("Cannot store file outside current directory");
        }

        return file;
    }

    public void createIfNotExists(Path path) throws IOException {
        // Create if not exists
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    public String getConfigStorageDepth(StorageDepth storageDepth) {
        return switch (storageDepth) {
            case UNENCRYPTED -> this.config.getUnencryptedFolder();
            case ENCRYPTED -> this.config.getEncryptedFolder();
            case NONE -> "";
        };
    }

    public enum StorageDepth {
        NONE, UNENCRYPTED, ENCRYPTED,
    }
}
