package ch.asu.chaosdb.core.entries.storage;

import ch.asu.chaosdb.core.entries.storage.encrypted.EncryptedFileStorageService;
import ch.asu.chaosdb.core.entries.storage.unencrypted.UnencryptedFileStorage;
import ch.asu.chaosdb.core.exception.customexceptions.StorageException;
import ch.asu.chaosdb.core.entries.storage.encrypted.EncryptedFileStorage;
import ch.asu.chaosdb.core.entries.storage.unencrypted.UnencryptedFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    private final EncryptedFileStorageService encryptedFileStorageService;
    private final UnencryptedFileStorageService unencryptedFileStorageService;
    private final StorageRepository repository;
    private final StorageConfig storageConfig;

    @Autowired
    public StorageServiceImpl(EncryptedFileStorageService encryptedFileStorageService, UnencryptedFileStorageService unencryptedFileStorageService, StorageRepository repository, StorageConfig storageConfig) {
        this.encryptedFileStorageService = encryptedFileStorageService;
        this.unencryptedFileStorageService = unencryptedFileStorageService;
        this.repository = repository;
        this.storageConfig = storageConfig;
    }

    @Override
    public Storage uploadFile(MultipartFile file) {
        return uploadFile(file, storageConfig.isDefaultFileEncryption());
    }

    @Override
    public Storage uploadFile(MultipartFile file, boolean encrypted) {
        if (encrypted) {
            return encryptedFileStorageService.save(file);
        } else {
            return unencryptedFileStorageService.save(file);
        }
    }

    @Override
    public ByteArrayResource getFileById(UUID id) {
        Storage storage = this.findById(id);

        if (storage instanceof UnencryptedFileStorage) {
            return unencryptedFileStorageService.getFileById(storage.getId());
        } else if (storage instanceof EncryptedFileStorage) {
            return encryptedFileStorageService.getFileById(storage.getId());
        }

        throw new StorageException("Encountered Problem: No inheritance for SIT found");
    }

    @Override
    public Storage findById(UUID id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
