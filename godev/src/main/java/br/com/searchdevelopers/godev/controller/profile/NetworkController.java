package br.com.searchdevelopers.godev.controller.profile;

import br.com.searchdevelopers.godev.domain.Network;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.NetworkRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/networks")
public class NetworkController {

    @Autowired
    private NetworkRepository networkRepository;

    private final RegisterUser registerUser;

    public NetworkController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/{id}")
    public ResponseEntity postNetwork(@Valid @RequestBody Network network,
                                    @PathVariable Integer id) {
        try {
            Optional<Users> users = registerUser.findByIdUser(id);
            network.setUsers(users.get());
            networkRepository.save(network);

            return ResponseEntity.ok(network);
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdNetwork(@PathVariable Integer id) {
        if(networkRepository.findById(id).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(networkRepository.findById(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity findByIdNetworkIdUser(@PathVariable Integer id) {
        if(networkRepository.existsById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(networkRepository.getOne(id));
        //Foi alterado para realizar um teste, para voltar como estava é só colar no return e descomentar
        //o find que está na network repository
        //networkRepository.findByUsersIdUser(id)
    }

    @GetMapping("/")
    public ResponseEntity getAllNetwork(){
        if (networkRepository.findAll().isEmpty()){
            return status(204).build();
        } else {
            return ResponseEntity.ok(networkRepository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putNetwork(@Valid @PathVariable Integer id, @RequestBody Network network) {
        if (networkRepository.existsById(id)) {
            network.setIdNetwork(id);
            networkRepository.save(network);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteNetwork(@PathVariable Integer id) {
        if (networkRepository.existsById(id)) {
            networkRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
