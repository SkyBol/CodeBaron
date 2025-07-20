package ch.asu.chaosdb.core.entries.storage.unencrypted;

import ch.asu.chaosdb.core.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnencryptedFileStorageRepository extends AbstractRepository<UnencryptedFileStorage> {
}
