package ch.asu.chaosdb.core.entries.storage.encrypted;

import ch.asu.chaosdb.core.abstracts.AbstractService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface EncryptedFileStorageService extends AbstractService<EncryptedFileStorage> {
    EncryptedFileStorage save(MultipartFile file);
    ByteArrayResource getFileById(UUID id);
}
