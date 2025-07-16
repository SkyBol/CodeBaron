package ch.asu.chaosdb.core.abstracts;

import ch.asu.chaosdb.core.abstracts.filter.DynamicFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractController<AE extends AbstractEntity, DTO extends AbstractDTO> {

  protected final AbstractService<AE> service;
  protected final AbstractMapper<AE, DTO> mapper;

  protected AbstractController(AbstractService<AE> service, AbstractMapper<AE, DTO> mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  public ResponseEntity<DTO> findById(@PathVariable UUID id) {
    AE entity = service.findById(id);
    return new ResponseEntity<>(mapper.toDTO(entity), HttpStatus.OK);
  }

  public ResponseEntity<List<DTO>> findAll(
      @RequestParam(required = false) Map<String, String> filter,
      Pageable pageable
  ) {
    List<AE> entity = service.findAll(DynamicFilter.from(filter), pageable);
    return new ResponseEntity<>(mapper.toDTOs(entity), HttpStatus.OK);
  }

  public ResponseEntity<DTO> create(@RequestBody DTO dto) {
    AE entity = service.save(mapper.fromDTO(dto));
    return new ResponseEntity<>(mapper.toDTO(entity), HttpStatus.OK);
  }

  public ResponseEntity<DTO> updateById(@PathVariable UUID id, @RequestBody DTO dto) {
    AE entity = service.updateById(id, mapper.fromDTO(dto));
    return new ResponseEntity<>(mapper.toDTO(entity), HttpStatus.OK);
  }

  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    service.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
