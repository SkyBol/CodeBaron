package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractService;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosText;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ChaosEntryService extends AbstractService<ChaosEntry> {
    long count();

    // File Handling
    ByteArrayResource loadFile(UUID id);
    ChaosEntry uploadFile(MultipartFile file);

    // Text Handling
    ChaosEntry saveText(ChaosText chaosText);

    // Novel Handling
    // ChaosEntry createNovel(ChaosContentDTO novel);
}
