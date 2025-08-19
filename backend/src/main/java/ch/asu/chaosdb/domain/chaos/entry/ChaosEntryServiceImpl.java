package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractServiceImpl;
import ch.asu.chaosdb.core.entries.storage.StorageService;
import ch.asu.chaosdb.domain.chaos.content.ChaosContent;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosFile;
import ch.asu.chaosdb.domain.chaos.content.types.ChaosText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

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
      public ByteArrayResource loadFile(UUID entryId) {
            return this.loadFile(entryId, 0);
      }
      @Override
      public ByteArrayResource loadFile(UUID entryId, int chaosContentFileNumber) {
            List<ChaosContent> chaosContents = getAllFileContents(entryId);

            if (chaosContents.isEmpty()) {
                  throw new NoSuchElementException("Entity has no FILE Types");
            }

            if (chaosContents.size() - 1 <= chaosContentFileNumber) {
                  UUID fileId = ((ChaosFile) chaosContents.get(chaosContentFileNumber)).getFileStorage().getId();
                  return storageService.getFileById(fileId);
            }

            throw new NoSuchElementException("Content only has '" + chaosContents.size() + "' FILE Types");
      }

      private List<ChaosContent> getAllFileContents(UUID entryId) {
            ChaosEntry entry = super.findById(entryId);

            return entry.getContent().stream()
                    .filter(chaosContent -> chaosContent.getMediaType().equals("FILE"))
                    .toList();
      }

      @Override
      public ChaosEntry uploadFile(MultipartFile file) {
            return this.uploadFile(List.of(file));
      }
      @Override
      public ChaosEntry uploadFile(List<MultipartFile> multipartFiles) {
            ChaosEntry entry = new ChaosEntry();

            ArrayList<ChaosContent> chaosFiles = new ArrayList<>();

            // Save Files
            for (MultipartFile file : multipartFiles) {
                  // Create new ChaosFile
                  ChaosFile chaosFile = new ChaosFile();
                  chaosFile.setFileStorage(storageService.uploadFile(file));
                  chaosFile.setFileType(getFileType(file));
                  chaosFile.setMediaType("FILE");

                  chaosFiles.add(chaosFile);
            }

            // Link to Contents
            entry.setContent(chaosFiles);

            return this.save(entry);
      }

      private ChaosFile.FileType getFileType(MultipartFile file) {
            String type = file.getContentType();
            if (type == null) {
                  return ChaosFile.FileType.DOCUMENT;
            }
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
            return this.saveText(List.of(chaosText));
      }
      @Override
      public ChaosEntry saveText(List<ChaosText> chaosTexts) {
            ChaosEntry entry = new ChaosEntry();

            // Necessary for Type Casting
            ArrayList<ChaosContent> chaosContents = new ArrayList<>(chaosTexts);

            // Link to Content
            entry.setContent(chaosContents);

            return this.save(entry);
      }
}
