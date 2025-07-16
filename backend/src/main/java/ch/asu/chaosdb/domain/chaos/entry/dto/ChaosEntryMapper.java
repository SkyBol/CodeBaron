package ch.asu.chaosdb.domain.chaos.entry.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentMapper;
import ch.asu.chaosdb.domain.chaos.entry.ChaosEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { ChaosContentMapper.class })
public interface ChaosEntryMapper extends AbstractMapper<ChaosEntry, ChaosEntryDTO> {
}
