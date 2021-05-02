package br.com.searchdevelopers.godev.controller.developer;

import br.com.searchdevelopers.godev.domain.Formation;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.FormationRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationRepository repository;

    private final RegisterUser registerUser;

    public FormationController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/{id}")
    public ResponseEntity postFormation(@Valid @RequestBody Formation formation,
                                    @PathVariable Integer id) {
        try {
            Optional<Users> users = registerUser.findByIdUser(id);
            formation.setUsers(users.get());
            repository.save(formation);

            return ResponseEntity.ok(formation);
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdFormation(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity findByIdFormationIdUser(@PathVariable Integer id) {
        if(repository.findByUsersIdUser(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findByUsersIdUser(id));
    }

    @GetMapping
    public ResponseEntity getAllFormation(){
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putFormation(@Valid @PathVariable Integer id, @RequestBody Formation formation) {
        if (repository.existsById(id)) {
            formation.setIdFormation(id);
            repository.save(formation);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteFormation(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
