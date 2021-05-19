package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.Tool;
import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.ToolRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private ToolRepository repository;

    private final RegisterUser registerUser;

    public ToolController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/{id}")
    public ResponseEntity postTool(@Valid @RequestBody Tool tool,
                                         @PathVariable Integer id) {
        try {
            Optional<User> users = registerUser.findByIdUser(id);
            tool.setUsers(users.get());
            repository.save(tool);

            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdTool(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity findByIdToolIdUser(@PathVariable Integer id) {
        if(repository.findByUserIdUser(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findByUserIdUser(id));
    }

    @GetMapping
    public ResponseEntity getAllTool(){
        if (repository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putTool(@Valid @PathVariable Integer id,
                                        @RequestBody Tool tool) {
        if (repository.existsById(id)) {
            tool.setIdTool(id);
            repository.save(tool);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTool(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
