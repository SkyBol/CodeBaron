package ch.asu.chaosdb.core.abstracts;

import ch.asu.chaosdb.core.abstracts.filter.AbstractSpecifications;
import ch.asu.chaosdb.core.abstracts.filter.DynamicFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@AllArgsConstructor
public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

  protected final AbstractRepository<T> repository;

  @Override
  public T save(T entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(UUID id) throws NoSuchElementException {
    if (repository.existsById(id)) {
      repository.deleteById(id);
    } else {
      throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
    }
  }

  @Override
  public T updateById(UUID id, T entity) throws NoSuchElementException {
    if (repository.existsById(id)) {
      entity.setId(id);
      return repository.save(entity);
    } else {
      throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
    }
  }

  @Override
  public List<T> findAll() {
    return this.findAll(null, null);
  }
  @Override
  public List<T> findAll(Pageable pageable) {
    return this.findAll(null, pageable);
  }
  @Override
  public List<T> findAll(DynamicFilter filter) {
    return this.findAll(filter, null);
  }
  @Override
  public List<T> findAll(DynamicFilter filter, Pageable pageable) {
    if (pageable == null) {
      Specification<T> specification = AbstractSpecifications.filter(filter);

      // Just returns all Objects
      return repository.findAll(specification);
    }

    return this.findAllPageable(filter, pageable).getContent();
  }

  @Override
  public Page<T> findAllPageable(DynamicFilter filter, Pageable pageable) {
    Specification<T> specification = AbstractSpecifications.filter(filter);
    return repository.findAll(specification, pageable);
  }

  @Override
  public T findById(UUID id) {
    return repository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public boolean existsById(UUID id) {
    return repository.existsById(id);
  }
}
