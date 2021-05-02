package br.com.searchdevelopers.godev.controller.developer;

import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.ExperienceRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceRepository repository;

    private final RegisterUser registerUser;

    public ExperienceController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/{id}")
    public ResponseEntity postExperience(@Valid @RequestBody Experience experience,
                                         @PathVariable Integer id) {
        try {
            Optional<Users> users = registerUser.findByIdUser(id);
            experience.setUsers(users.get());
            repository.save(experience);

            return ResponseEntity.ok(experience);
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdExperience(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }


    @GetMapping(path = "/user/{id}")
    public ResponseEntity findByIdExperienceIdUser(@PathVariable Integer id) {
        if(repository.findByUsersIdUser(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findByUsersIdUser(id));
    }

    @GetMapping
    public ResponseEntity getAllExperience(){
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putExperience(@Valid @PathVariable Integer id,
                                        @RequestBody Experience experience) {
        if (repository.existsById(id)) {
            experience.setIdExperience(id);
            repository.save(experience);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteExperience(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
