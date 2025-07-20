package ch.asu.chaosdb.core.entries.storage.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.core.entries.storage.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorageMapper extends AbstractMapper<Storage, StorageDTO> {
}
