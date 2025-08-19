package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractService;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosText;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ChaosEntryService extends AbstractService<ChaosEntry> {
    long count();

    // File Handling
    ByteArrayResource loadFile(UUID id);
    ByteArrayResource loadFile(UUID id, int chaosContentFileNumber);
    ChaosEntry uploadFile(MultipartFile file);
    ChaosEntry uploadFile(List<MultipartFile> file);

    // Text Handling
    ChaosEntry saveText(ChaosText chaosText);
    ChaosEntry saveText(List<ChaosText> chaosTexts);

    // Novel Handling
    // ChaosEntry createNovel(ChaosContentDTO novel);
}
