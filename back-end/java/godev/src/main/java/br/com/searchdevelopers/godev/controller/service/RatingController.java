package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import br.com.searchdevelopers.godev.usecases.RegisterRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    private RegisterRating registerRating;

    public RatingController(RegisterRating registerRating) {
        this.registerRating = registerRating;
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

    @PutMapping(path = "/{idService}/service/{idUserDev}/user")
    public ResponseEntity putService(@PathVariable Integer idService,
                                     @PathVariable Integer idUserDev) {
        try{
            UserService userService = new UserService();
            Optional<Users> users = userRepository.findById(idUserDev);
            Optional<br.com.searchdevelopers.godev.domain.Service> service = serviceRepository.findById(idService);

            userService.setService(service.get());
            userService.setUserDev(users.get());
            userService.getUserDev().setStarsUser(
                    registerRating.validateRating(userService.getUserDev().getRatingsCont(),
                            userService.getUserDev().getRatingsSum(),
                            userService.getService().getStarts()));
            userServiceRepository.save(userService);
            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
