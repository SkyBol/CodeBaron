package ch.asu.chaosdb.core.entries.storage.unencrypted;

import ch.asu.chaosdb.core.abstracts.AbstractRepository;
import ch.asu.chaosdb.core.abstracts.AbstractServiceImpl;
import ch.asu.chaosdb.core.entries.storage.helpers.StorageHelper;
import ch.asu.chaosdb.core.exception.customexceptions.StorageException;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class UnencryptedFileStorageServiceImpl extends AbstractServiceImpl<UnencryptedFileStorage> implements UnencryptedFileStorageService {

    private final StorageHelper helper;

    @Autowired
    public UnencryptedFileStorageServiceImpl(AbstractRepository<UnencryptedFileStorage> repository, StorageHelper helper) {
        super(repository);
        this.helper = helper;
    }

    public UnencryptedFileStorage save(MultipartFile file) {
        try {
            // Check file integrity
            if (file.isEmpty() || Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            // Creates a simple storage Object, and saves it to the DB for an ID
            UnencryptedFileStorage unencryptedFileStorage = buildUnencryptedFileStorage(file);

            Path filePath = helper.generateFileStoragePath(String.valueOf(unencryptedFileStorage.getId()), StorageHelper.StorageDepth.ENCRYPTED);

            // Write file to disc
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
                fileOutputStream.write(file.getInputStream().readAllBytes());
            } catch (Exception e) {
                throw new StorageException("Encountered a Problem while writing the file to directory", e);
            }

            // This updateById is somewhat unnecessary
            //      But is here for comparison to EncryptedService
            return super.updateById(unencryptedFileStorage.getId(), unencryptedFileStorage);
        } catch (Exception e) {
            throw new StorageException("Error during file save", e);
        }
    }
    private UnencryptedFileStorage buildUnencryptedFileStorage(MultipartFile file) throws IOException {
        UnencryptedFileStorage unencryptedFileStorage = new UnencryptedFileStorage();

        unencryptedFileStorage.setFileName(file.getOriginalFilename());
        unencryptedFileStorage.setFileType((file.getContentType()));

        String originalFileHash = Hashing.sha256().hashBytes(file.getBytes()).toString();
        unencryptedFileStorage.setOriginalFileChecksum(originalFileHash);

        return super.save(unencryptedFileStorage);
    }

    // Deletes the old File and Saves the new one, effectively overriding the content
    @Override
    public UnencryptedFileStorage updateById(UUID id, UnencryptedFileStorage entity) throws NoSuchElementException {
        return super.updateById(id, entity);
        // TODO
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        UnencryptedFileStorage unencryptedFileStorage = super.findById(id);

        try {
            Path file = helper.generateFileStoragePath(unencryptedFileStorage.getId().toString(), StorageHelper.StorageDepth.ENCRYPTED);

            if (!file.toFile().exists() || !file.toFile().delete()) {
                throw new StorageException("Couldn't delete File on Server");
            }

            super.deleteById(unencryptedFileStorage.getId());
        } catch (Exception e) {
            throw new StorageException(String.format("Couldn't delete file '%s'", unencryptedFileStorage.getId()));
        }
    }

    public ByteArrayResource getFileById(UUID id) {
        try {
            // Get Storage Object
            UnencryptedFileStorage unencryptedFileStorage = super.findById(id);

            // Check if checksum exists
            if (unencryptedFileStorage.getOriginalFileChecksum() == null) {
                throw new StorageException("Checksum of encrypted file is null! Please contact the administrator for help");
            }

            Path file = helper.generateFileStoragePath(unencryptedFileStorage.getId().toString(), StorageHelper.StorageDepth.ENCRYPTED);

            try (InputStream inputStream = new FileInputStream(file.toFile())) {
                byte[] unencryptedFile = inputStream.readAllBytes();
                String checksum = Hashing.sha256().hashBytes(unencryptedFile).toString();

                if (!unencryptedFileStorage.getOriginalFileChecksum().equals(checksum)) {
                    throw new StorageException(String.format("File checksum doesn't match! Saved checksum: '%s', now is: '%s'", unencryptedFileStorage.getOriginalFileChecksum(), checksum));
                }

                return new ByteArrayResource(unencryptedFile);
            }
        } catch (Exception e) {
            throw new StorageException("Error during loading of saved file", e);
        }
    }
}
