package ch.asu.chaosdb.domain.chaos.entry;

import ch.asu.chaosdb.core.abstracts.AbstractController;
import ch.asu.chaosdb.domain.chaos.content.dto.ChaosContentMapper;
import ch.asu.chaosdb.domain.chaos.content.types.dto.ChaosTextDTO;
import ch.asu.chaosdb.domain.chaos.entry.dto.ChaosEntryDTO;
import ch.asu.chaosdb.domain.chaos.entry.dto.ChaosEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chaos")
public class ChaosEntryController extends AbstractController<ChaosEntry, ChaosEntryDTO> {
    @Autowired
    protected ChaosEntryController(ChaosEntryService service, ChaosEntryMapper mapper, ChaosContentMapper chaosContentMapper) {
        super(service, mapper);
        this.chaosContentMapper = chaosContentMapper;
    }

    private final ChaosContentMapper chaosContentMapper;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ChaosEntryDTO> findById(@PathVariable UUID id) {
        return super.findById(id);
    }

    @GetMapping({"", "/"})
    @Override
    public ResponseEntity<List<ChaosEntryDTO>> findAll(@RequestParam(required = false) Map<String, String> filter, Pageable pageable) {
        return super.findAll(filter, pageable);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(((ChaosEntryService) service).count());
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        return super.deleteById(id);
    }


    /**
     * File Handling
     */

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> loadFileAsResource(@PathVariable UUID id) {
        ByteArrayResource byteArrayResource = ((ChaosEntryService) service).loadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(byteArrayResource.contentLength())
                .body(byteArrayResource);
    }

    @PostMapping("/upload")
    public ResponseEntity<ChaosEntryDTO> upload(@RequestParam("file") MultipartFile file) {
        ChaosEntry chaosEntry = ((ChaosEntryService) service).uploadFile(file);

        return ResponseEntity.ok(mapper.toDTO(chaosEntry));
    }


    /**
     * Text Handling
     */

    @PostMapping("/text")
    public ResponseEntity<ChaosEntryDTO> createText(@RequestBody ChaosTextDTO chaosTextDTO) {
        ChaosEntry chaosEntry = ((ChaosEntryService) service).saveText(chaosContentMapper.fromDTO(chaosTextDTO));

        return ResponseEntity.ok(mapper.toDTO(chaosEntry));
    }
}
