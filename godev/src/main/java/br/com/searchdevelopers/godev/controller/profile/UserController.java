package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.usecases.PhotoService;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final RegisterUser registerUser;

    private final PhotoService photoService;

    public UserController(UserRepository userRepository,
                          RegisterUser registerUser,
                          PhotoService photoService) {
        this.userRepository = userRepository;
        this.registerUser = registerUser;
        this.photoService = photoService;
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
        if (userRepository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(userRepository.findAll());
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getTypeUsers(@PathVariable int id) {
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
    public ResponseEntity putUser(@Valid @PathVariable int id, @RequestBody Users users) {
        if (userRepository.existsById(id)) {
            users.setIdUser(id);
            userRepository.save(users);
            return ResponseEntity.created(null).build();
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

    @GetMapping("/photo/{id}")
    public ResponseEntity getPhotoUser(@PathVariable Integer id) {
        try{
            userRepository.existsByIdUser(id);
            return ResponseEntity.ok(userRepository.findByPhoto());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path = "/photo/{id}")
    public ResponseEntity photoUser(@PathVariable Integer id,
                                    @RequestParam MultipartFile file) throws IOException {
        try{
            userRepository.existsByIdUser(id);
            photoService.savePhotoUser(id, file);
            return ResponseEntity.ok(null);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
