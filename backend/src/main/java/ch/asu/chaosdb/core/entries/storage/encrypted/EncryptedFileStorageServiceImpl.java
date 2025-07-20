package ch.asu.chaosdb.core.entries.storage.encrypted;

import ch.asu.chaosdb.core.abstracts.AbstractServiceImpl;
import ch.asu.chaosdb.core.exception.customexceptions.StorageException;
import ch.asu.chaosdb.core.entries.storage.StorageConfig;
import ch.asu.chaosdb.core.entries.storage.helpers.EnDeCrypter;
import ch.asu.chaosdb.core.entries.storage.helpers.StorageHelper;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class EncryptedFileStorageServiceImpl extends AbstractServiceImpl<EncryptedFileStorage> implements EncryptedFileStorageService {

    private final StorageConfig config;
    private final StorageHelper helper;

    @Autowired
    public EncryptedFileStorageServiceImpl(EncryptedFileStorageRepository repository, StorageConfig config, StorageHelper helper) {
        super(repository);
        this.config = config;
        this.helper = helper;
    }

    public EncryptedFileStorage save(MultipartFile file) {
        try {
            // Check file integrity
            if (file.isEmpty() || Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            // Creates a simple storage Object, and saves it to the DB for an ID
            EncryptedFileStorage encryptedFileStorage = buildEncryptedFileStorage(file);

            Path filePath = helper.generateFileStoragePath(String.valueOf(encryptedFileStorage.getId()), StorageHelper.StorageDepth.ENCRYPTED);
            // Encrypt File
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
                EnDeCrypter enCrypter = new EnDeCrypter(encryptedFileStorage.getKey(), encryptedFileStorage.getSalt(), config.getPepper());
                byte[] encryptedFile = enCrypter.encrypt(file.getInputStream().readAllBytes());

                fileOutputStream.write(encryptedFile);
                String encryptedFileHash = Hashing.sha256().hashBytes(encryptedFile).toString();
                encryptedFileStorage.setEncryptedFileChecksum(encryptedFileHash);
            } catch (Exception e) {
                throw new StorageException("Encountered a Problem during File Encryption", e);
            }

            // Update for Encryption Checksum, then return
            return super.updateById(encryptedFileStorage.getId(), encryptedFileStorage);
        } catch (Exception e) {
            throw new StorageException("Error during file save", e);
        }
    }
    private EncryptedFileStorage buildEncryptedFileStorage(MultipartFile file) throws IOException {
        EncryptedFileStorage encryptedFileStorage = new EncryptedFileStorage();

        encryptedFileStorage.setFileName(file.getOriginalFilename());
        encryptedFileStorage.setFileType((file.getContentType()));
        encryptedFileStorage.setSalt(KeyGenerators.string().generateKey());

        String originalFileHash = Hashing.sha256().hashBytes(file.getBytes()).toString();
        encryptedFileStorage.setOriginalFileChecksum(originalFileHash);

        // This key will be used to encrypt the file
        //      A hash from the originalChecksum, FileName and FileType
        String key = Hashing.sha256()
                .hashString(String.format(
                        "%s%s%s",
                        encryptedFileStorage.getOriginalFileChecksum(),
                        encryptedFileStorage.getFileName(),
                        encryptedFileStorage.getFileType()
                ), Charset.defaultCharset())
                .toString();
        encryptedFileStorage.setKey(key);

        return super.save(encryptedFileStorage);
    }

    // Deletes the old File and Saves the new one, effectively overriding the content
    @Override
    public EncryptedFileStorage updateById(UUID id, EncryptedFileStorage entity) throws NoSuchElementException {
        return super.updateById(id, entity);
        // TODO
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        EncryptedFileStorage encryptedFileStorage = super.findById(id);

        try {
            Path file = helper.generateFileStoragePath(encryptedFileStorage.getId().toString(), StorageHelper.StorageDepth.ENCRYPTED);

            if (!file.toFile().exists() || !file.toFile().delete()) {
                throw new StorageException("Couldn't delete File on Server");
            }

            super.deleteById(encryptedFileStorage.getId());
        } catch (Exception e) {
            throw new StorageException(String.format("Couldn't delete file '%s'", encryptedFileStorage.getId()));
        }
    }

    public ByteArrayResource getFileById(UUID id) {
        try {
            // Get stored Object
            EncryptedFileStorage encryptedFileStorage = super.findById(id);

            // Check if checksum exists
            if (encryptedFileStorage.getEncryptedFileChecksum() == null) {
                throw new StorageException("Checksum of encrypted file is null! Please contact the administrator for help");
            }

            // Get reference to encrypted File
            Path file = helper.generateFileStoragePath(encryptedFileStorage.getId().toString(), StorageHelper.StorageDepth.ENCRYPTED);

            try (InputStream inputStream = new FileInputStream(file.toFile())) {
                byte[] encryptedFile = inputStream.readAllBytes();
                String encryptedChecksum = Hashing.sha256().hashBytes(encryptedFile).toString();
                // Check checksum
                if (!encryptedFileStorage.getEncryptedFileChecksum().equals(encryptedChecksum)) {
                    throw new StorageException(String.format("Encrypted checksum doesn't match! Saved checksum: '%s', now is: '%s'", encryptedFileStorage.getEncryptedFileChecksum(), encryptedChecksum));
                }

                // Decrypt file
                EnDeCrypter deCrypter = new EnDeCrypter(encryptedFileStorage.getKey(), encryptedFileStorage.getSalt(), config.getPepper());
                byte[] decryptedFile = deCrypter.decrypt(encryptedFile);

                // Check checksum
                String fileChecksum = Hashing.sha256().hashBytes(decryptedFile).toString();
                if (!encryptedFileStorage.getOriginalFileChecksum().equals(fileChecksum)) {
                    throw new StorageException(String.format("Decrypted checksum doesn't match! Saved checksum: '%s', now is: '%s'", encryptedFileStorage.getOriginalFileChecksum(), fileChecksum));
                }

                return new ByteArrayResource(decryptedFile);
            }
        } catch (Exception e) {
            throw new StorageException("Error during loading of saved file", e);
        }
    }
}
