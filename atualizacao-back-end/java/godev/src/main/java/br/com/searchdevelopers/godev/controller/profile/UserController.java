package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.controller.service.StorageService;
import br.com.searchdevelopers.godev.domain.Photo;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.PhotoRepository;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository repository;

  @Autowired
  private PhotoRepository photoRepository;

  private final RegisterUser registerUser;

  @Autowired
  private StorageService storageService;

  public UserController(RegisterUser registerUser) {
    this.registerUser = registerUser;
  }

  @PostMapping
  public ResponseEntity postUser(@Valid @RequestBody Users users) {
    try {
      registerUser.saveUser(users);
      return ResponseEntity.created(null).build();
    } catch (BusinessRuleException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/authenticate")
  public ResponseEntity authenticate(@RequestBody Users users) {
    try {
      Users usersAuthenticate = registerUser.authenticate(users.getEmail(), users.getPassword());
      return ResponseEntity.ok(usersAuthenticate);
    } catch (AuthenticationErrorException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity getAllServices() {
    if (repository.findAll().isEmpty()) {
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAll());
    }
  }

  @GetMapping("/type/{id}")
  public ResponseEntity getTypeUsers(@PathVariable int id) {
    List<Users> devs = repository.findByRoleEquals("dev");
    List<Users> clients = repository.findByRoleEquals("clt");

    boolean isDev = repository.existsByIdUserAndRoleEquals(id, "dev");
    boolean isClt = repository.existsByIdUserAndRoleEquals(id, "clt");

    if (isDev) {
      return ResponseEntity.ok(clients);
    } else if (isClt) {
      return ResponseEntity.ok(devs);
    } else {
      return ResponseEntity.badRequest().build();
    }

  }

  @GetMapping(path = "/{id}")
  public ResponseEntity findByIdUser(@PathVariable int id) {
    if (repository.findById(id).isPresent()) {
      return ResponseEntity.ok(repository.findById(id));
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity putUser(@Valid @PathVariable int id, @RequestBody Users users) {
    if (repository.existsById(id)) {
      users.setIdUser(id);
      repository.save(users);
      return ResponseEntity.created(null).build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteUser(@PathVariable int id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/file/{id}")
  public ResponseEntity createAttachment(@PathVariable int id,
                                         @RequestParam MultipartFile file) throws IOException {
    Optional<Users> users = registerUser.findByIdUser(id);

    if (file.isEmpty()) {
      return ResponseEntity.status(400).build();
    }

    if (users.isEmpty()) {
      return  ResponseEntity.status(404).build();
    }

    String path = storageService.savePhoto(file);
    Users user = users.get();
    user.setPhoto(path);
    repository.save(user);
    return ResponseEntity.status(201).build();
  }

  @GetMapping("/file/{id}")
  public ResponseEntity findByIdPhotoUser() {
    if (photoRepository.findAll().isEmpty()) {
      return status(204).build();
    } else {
      return ResponseEntity.ok(photoRepository.findAll());
    }
  }

}
