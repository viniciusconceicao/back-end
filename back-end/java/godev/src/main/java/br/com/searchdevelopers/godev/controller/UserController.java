package br.com.searchdevelopers.godev.controller;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.UsersRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersRepository repository;

    private final RegisterUser registerUser;

    public UserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping
    public ResponseEntity postUser (@Valid @RequestBody Users users){
        try{
            registerUser.saveUser(users);
            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate ( @RequestBody Users users){
        try {
            Users userAuthenticate = registerUser.authenticate(users.getEmail(), users.getPassword());
            return ResponseEntity.ok(userAuthenticate);
        } catch (AuthenticationErrorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dev")
    public ResponseEntity getDevUsers(){
        List<Users> devs = repository.findByRoleContaining("dev");
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(devs);
        }
    }

    @GetMapping("/clt")
    public ResponseEntity getClientUsers(){
        List<Users> clients = repository.findByRoleContaining("clt");
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(clients);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdUser(@PathVariable int id){
        if (repository.findById(id).isPresent()){
            return ResponseEntity.ok(repository.findById(id));
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putUser(@PathVariable int id, @RequestBody Users users){
        if (repository.existsById(id)){
            users.setIdUser(id);
            repository.save(users);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
