package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaosEntryRepository extends AbstractRepository<ChaosEntry> {
}
