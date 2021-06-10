package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import br.com.searchdevelopers.godev.usecases.RegisterService;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private ServiceRepository repository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    private final RegisterService registerService;
    private final RegisterUser registerUser;

    public RatingController(RegisterService registerService, RegisterUser registerUser) {
        this.registerService = registerService;
        this.registerUser = registerUser;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdServiceRating(@PathVariable Integer id) {
        List<UserService> serviceRating = userServiceRepository
                .findByUsersDevIdUserAndServiceIsNotNull(id);
        if (serviceRating.isEmpty()) {
            return ResponseEntity.ok(serviceRating);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putService(@Valid @PathVariable Integer id,
                                     @RequestBody Service service) {
        if (repository.existsById(id)) {
            service.setIdService(id);
            if(service.getProgress() >= 100){
                service.setActiveService(false);
            }
            repository.save(service);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
