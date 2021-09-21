package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.CreditCard;
import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.CreditCardRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/card")
public class CreditCardController {

  @Autowired
  private CreditCardRepository repository;

  private final RegisterUser registerUser;

  public CreditCardController(RegisterUser registerUser) {
    this.registerUser = registerUser;
  }

  @PostMapping("/{id}")
  public ResponseEntity postCreditCard(@Valid @RequestBody CreditCard creditCard,
                                      @PathVariable Integer id) {
    try {
      Optional<Users> users = registerUser.findByIdUser(id);
      creditCard.setUsers(users.get());
      repository.save(creditCard);

      return ResponseEntity.created(null).build();
    } catch (BusinessRuleException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity findByIdCreditCard(@PathVariable Integer id) {
    if(repository.findById(id).isEmpty()){
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(repository.findById(id));
  }


  @GetMapping(path = "/user/{id}")
  public ResponseEntity findByIdCreditCardIdUser(@PathVariable Integer id) {
    if(repository.findByUsersIdUser(id).isEmpty()){
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(repository.findByUsersIdUser(id));
  }

//  @GetMapping
//  public ResponseEntity getAllCreditCards(){
//    if (repository.findAll().isEmpty()){
//      return status(204).build();
//    } else {
//      return ResponseEntity.ok(repository.findAll());
//    }
//  }

  @PutMapping(path = "/{id}")
  public ResponseEntity putCreditCard(@Valid @PathVariable Integer id,
                                      @RequestBody CreditCard creditCard) {
    if (repository.existsById(id)) {
      creditCard.setIdCreditCard(id);
      repository.save(creditCard);
      return ResponseEntity.created(null).build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteCreditCard(@PathVariable Integer id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
