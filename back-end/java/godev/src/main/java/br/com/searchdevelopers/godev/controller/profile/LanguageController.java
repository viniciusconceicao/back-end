package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.Language;
import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.LanguageRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository repository;

    private final RegisterUser registerUser;

    public LanguageController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/{id}")
    public ResponseEntity postLanguage(@Valid @RequestBody Language language,
                                         @PathVariable Integer id) {
        try {
            Optional<User> users = registerUser.findByIdUser(id);
            language.setUsers(users.get());
            repository.save(language);

            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdLanguage(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity findByIdLanguageIdUser(@PathVariable Integer id) {
        if(repository.findByUserIdUser(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findByUserIdUser(id));
    }

    @GetMapping
    public ResponseEntity getAllLanguages(){
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putLanguage(@Valid @PathVariable Integer id,
                                        @RequestBody Language language) {
        if (repository.existsById(id)) {
            language.setIdLanguage(id);
            repository.save(language);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteLanguage(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
