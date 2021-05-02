package br.com.searchdevelopers.godev.controller;

import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Network;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.NetworkRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
            network.setUser(users.get());
            networkRepository.save(network);

            return ResponseEntity.ok(network);
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public Optional<Network> findByIdExperience(@PathVariable Integer id) {
        if(networkRepository.findById(id).isEmpty()){
            throw new SearchErrorException("Network não encontrado com esse id informado.");
        }
        return networkRepository.findById(id);
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
