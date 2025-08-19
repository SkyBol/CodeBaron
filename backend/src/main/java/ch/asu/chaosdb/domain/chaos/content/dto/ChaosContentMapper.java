package ch.asu.chaosdb.domain.chaos.content.dto;

import ch.asu.chaosdb.core.abstracts.AbstractMapper;
import ch.asu.chaosdb.domain.chaos.content.ChaosContent;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosFile;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosText;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosFileDTO;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Log4j2
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChaosContentMapper implements AbstractMapper<ChaosContent, ChaosContentDTO> {
    private static final String badMappingMessage = "Possible bad mapping Call for object: {} - {}";

    /**
     * TEXT
     */
    @Mapping(target = "mediaType", constant = "TEXT")
    public abstract ChaosText fromDTO(ChaosTextDTO dto);

    @Mapping(target = "mediaType", constant = "TEXT")
    public abstract ChaosTextDTO toDTO(ChaosText BO);

    /**
     * FILE
     */
    @Mapping(target = "mediaType", constant = "FILE")
    @Mapping(target = "fileStorage", source = "storage")
    public abstract ChaosFile fromDTO(ChaosFileDTO dto);

    @Mapping(target = "mediaType", constant = "FILE")
    @Mapping(target = "storage", source = "fileStorage")
    public abstract ChaosFileDTO toDTO(ChaosFile BO);

    /**
     * Manual polymorphic mapping entry point
     */
    public ChaosContentDTO toDTO(ChaosContent content) {
        if (content instanceof ChaosText text) {
            return toDTO(text);
        } else if (content instanceof ChaosFile file) {
            return toDTO(file);
        }
        log.warn(badMappingMessage, content.getId(), content.getMediaType());
        return toDTO(content);
    }

    public ChaosContent fromDTO(ChaosContentDTO dto) {
        if (dto instanceof ChaosTextDTO text) {
            return fromDTO(text);
        } else if (dto instanceof ChaosFileDTO file) {
            return fromDTO(file);
        }
        log.warn(badMappingMessage, dto.getId(), dto.getMediaType());
        return fromDTO(dto);
    }
}
