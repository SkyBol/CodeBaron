package ch.asu.chaosdb.core.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
}
