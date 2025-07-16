package ch.asu.chaosdb.core.entries.access.user;

import ch.asu.chaosdb.core.abstracts.AbstractController;
import ch.asu.chaosdb.core.entries.access.user.dto.UserDTO;
import ch.asu.chaosdb.core.entries.access.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<User, UserDTO> {

  @Autowired
  protected UserController(UserService service, UserMapper mapper) {
    super(service, mapper);
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserDTO> findById(@PathVariable UUID id) {
    return super.findById(id);
  }

  @GetMapping({"", "/"})
  @Override
  public ResponseEntity<List<UserDTO>> findAll(@RequestParam(required = false) Map<String, String> filter, Pageable pageable) {
    return super.findAll(filter, pageable);
  }

  @PostMapping("/register")
  @Override
  public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
    return super.create(dto);
  }

  @PutMapping("/{id}")
  @Override
  public ResponseEntity<UserDTO> updateById(@PathVariable UUID id, @RequestBody UserDTO dto) {
    return super.updateById(id, dto);
  }

  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    return super.deleteById(id);
  }
}
