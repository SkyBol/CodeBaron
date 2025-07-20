package ch.asu.chaosdb.core.entries.storage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StorageService {
  // Default Upload; Auto-Encrypts
  Storage uploadFile(MultipartFile file);
  Storage uploadFile(MultipartFile file, boolean encrypted);

  ByteArrayResource getFileById(UUID id);

  Storage findById(UUID id);
}
