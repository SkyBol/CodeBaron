package ch.asu.chaosdb.core.entries.access.role.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.core.entries.access.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends AbstractMapper<Role, RoleDTO> {
}
