package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.controller.service.StorageService;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.dto.CreditCardDto;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.controller.service.RateUserService;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  private final RegisterUser registerUser;

  @Autowired
  private StorageService storageService;

  public UserController(RegisterUser registerUser) {
    this.registerUser = registerUser;
  }

  @PostMapping
  public ResponseEntity postUser(@Valid @RequestBody Users users) {
    try {
      users.setPremium(false);
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
    if (userRepository.findAll().isEmpty()) {
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(userRepository.findAll());
    }
  }

  @GetMapping("/type/{id}")
  public ResponseEntity getTypeUser(@PathVariable int id) {
    List<Users> devs = userRepository.findByRoleEquals("dev");
    List<Users> clients = userRepository.findByRoleEquals("clt");

    boolean isDev = userRepository.existsByIdUserAndRoleEquals(id, "dev");
    boolean isClt = userRepository.existsByIdUserAndRoleEquals(id, "clt");

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
    if (userRepository.findById(id).isPresent()) {
      return ResponseEntity.ok(userRepository.findById(id));
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity putUser(@PathVariable int id, @RequestBody Users users) {
    if (userRepository.existsById(id)) {
      users.setIdUser(id);
      userRepository.save(users);
      return ResponseEntity.created(null).build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /*
  Rota para cadastrar cartão de crédito
  */
  @PutMapping(path = "/card/{id}")
  public ResponseEntity addCardUser(@Valid @PathVariable int id, @RequestBody CreditCardDto creditCard) {

    if (userRepository.existsById(id)) {
      Users users = userRepository.findUserByIdUser(id);
      users.setIdUser(id);
      users.setNumberCard(creditCard.getNumberCard());
      users.setCvv(creditCard.getCvv());
      users.setMonthCard(creditCard.getMonthCard());
      users.setYearCard(creditCard.getYearCard());
      userRepository.save(users);
      return ResponseEntity.created(null).build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PutMapping(path = "/avaliar/{id}/{numStars}")
  public ResponseEntity rateUser(@Valid @PathVariable int id, @PathVariable Double numStars) {

    if (userRepository.existsById(id)) {
      Users users = userRepository.findUserByIdUser(id);
      RateUserService rate = new RateUserService();
      rate.avaliar(numStars, id, userRepository);
      userRepository.save(users);
      return ResponseEntity.ok(users.getStarsUser());
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteUser(@PathVariable int id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/file/{id}")
  public ResponseEntity createAttachment(@PathVariable int id,
                                         @RequestParam MultipartFile file) throws IOException {
    Optional<Users> user = registerUser.findByIdUser(id);

    if (file.isEmpty()) {
      return ResponseEntity.status(400).build();
    }

    if (user.isEmpty()) {
      return  ResponseEntity.status(404).build();
    }

    String path = storageService.savePhoto(file);
    Users usersU = user.get();
    usersU.setPhoto(path);
    userRepository.save(usersU);
    return ResponseEntity.status(201).build();
  }

}
