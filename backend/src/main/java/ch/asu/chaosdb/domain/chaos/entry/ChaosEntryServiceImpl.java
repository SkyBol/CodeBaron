package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractServiceImpl;
import ch.asu.chaosdb.core.entries.storage.StorageService;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentDTO;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosFile;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosText;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class ChaosEntryServiceImpl extends AbstractServiceImpl<ChaosEntry> implements ChaosEntryService {
      private final StorageService storageService;

      @Autowired
      public ChaosEntryServiceImpl(ChaosEntryRepository repository, StorageService storageService) {
            super(repository);
            this.storageService = storageService;
      }

      @Override
      public long count() {
            return repository.count();
      }

      @Override
      public ChaosEntry save(ChaosEntry entity) {
            entity.setCreatedAt(LocalDateTime.now());
            return super.save(entity);
      }

      /** File Handling start */

      @Override
      public ByteArrayResource loadFile(UUID id) {
            ChaosEntry entry = super.findById(id);
            if (!entry.getChaosContent().getMediaType().equals("FILE")) {
                throw new InvalidMediaTypeException(entry.getChaosContent().getMediaType(), "Not a FILE type!");
            }
            UUID fileId = ((ChaosFile) entry.getChaosContent()).getFileStorage().getId();

            return storageService.getFileById(fileId);
      }

      @Override
      public ChaosEntry uploadFile(MultipartFile file) {
            ChaosEntry entry = new ChaosEntry();

            // Save file
            ChaosFile content = new ChaosFile();
            content.setFileStorage(storageService.uploadFile(file));
            content.setFileType(getFileType(file));
            content.setMediaType("FILE");

            // Link to Content
            entry.setChaosContent(content);

            return this.save(entry);
      }

      private ChaosFile.FileType getFileType(MultipartFile file) {
            String type = file.getContentType();
            return switch (type.substring(0, type.indexOf('/'))) {
                  case "image" -> ChaosFile.FileType.IMAGE;
                  case "audio" -> ChaosFile.FileType.AUDIO;
                  case "video" -> ChaosFile.FileType.VIDEO;
                  default -> ChaosFile.FileType.DOCUMENT;
            };
      }


      /** Text Handling start */

      @Override
      public ChaosEntry saveText(ChaosText chaosText) {
            ChaosEntry entry = new ChaosEntry();

            // Link to Content
            entry.setChaosContent(chaosText);

            return this.save(entry);
      }
}
