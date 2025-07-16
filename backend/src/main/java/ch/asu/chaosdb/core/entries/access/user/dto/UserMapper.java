package ch.asu.chaosdb.core.entries.access.user.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.core.entries.access.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<User, UserDTO> {
}
