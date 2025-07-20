package ch.asu.chaosdb.core.entries.storage.unencrypted;

import ch.asu.chaosdb.core.abstracts.AbstractService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UnencryptedFileStorageService extends AbstractService<UnencryptedFileStorage> {
    UnencryptedFileStorage save(MultipartFile file);
    ByteArrayResource getFileById(UUID id);
}
