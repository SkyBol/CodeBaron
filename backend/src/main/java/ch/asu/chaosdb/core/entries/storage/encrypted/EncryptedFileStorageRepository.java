package ch.asu.chaosdb.core.entries.storage.encrypted;

import ch.asu.chaosdb.core.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptedFileStorageRepository extends AbstractRepository<EncryptedFileStorage> {
}
