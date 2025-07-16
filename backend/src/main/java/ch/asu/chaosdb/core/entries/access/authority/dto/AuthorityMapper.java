package ch.asu.chaosdb.core.entries.access.authority.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.core.entries.access.authority.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends AbstractMapper<Authority, AuthorityDTO> {
}
