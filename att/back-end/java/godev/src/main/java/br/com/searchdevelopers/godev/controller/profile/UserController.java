package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    private final RegisterUser registerUser;

    public UserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping
    public ResponseEntity postUser (@Valid @RequestBody User user){
        try{
            registerUser.saveUser(user);
            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate (@RequestBody User user){
        try {
            User userAuthenticate = registerUser.authenticate(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(userAuthenticate);
        } catch (AuthenticationErrorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllServices(){
        if (repository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getTypeUsers(@PathVariable int id){
        List<User> devs = repository.findByRoleEquals("dev");
        List<User> clients = repository.findByRoleEquals("clt");

        boolean isDev = repository.existsByIdUserAndRoleEquals(id, "dev");
        boolean isClt = repository.existsByIdUserAndRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.ok(clients);
        } else if (isClt){
            return ResponseEntity.ok(devs);
        } else {
            return ResponseEntity.noContent().build();
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
    public ResponseEntity putUser(@PathVariable int id, @RequestBody User user){
        if (repository.existsById(id)){
            user.setIdUser(id);
            repository.save(user);
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

    @PutMapping("/file/{id}")
    public ResponseEntity createAttachment(@PathVariable int id,
                                           @RequestParam MultipartFile file,
                                           @RequestBody User user) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.status(400).build();
        }

        user.setNamePhoto(file.getOriginalFilename());
        user.setPhotoContent(file.getBytes());

        if (repository.existsById(id)){
            user.setIdUser(id);
        }

        repository.save(user);
        return ResponseEntity.status(201).build();
    }

}
