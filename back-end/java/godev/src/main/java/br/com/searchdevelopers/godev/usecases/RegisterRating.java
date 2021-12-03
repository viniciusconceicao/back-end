package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterRating {

    private UserRepository userRepository;
    private ServiceRepository serviceRepository;
    private UserServiceRepository userServiceRepository;

    @Autowired
    public RegisterRating(UserRepository userRepository,
                          ServiceRepository serviceRepository,
                          UserServiceRepository userServiceRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.userServiceRepository = userServiceRepository;
    }

    public UserService starRating(Integer idService,
                           Integer idUserDev) {

        UserService userService = new UserService();
        Optional<Users> user = userRepository.findById(idUserDev);
        Optional<br.com.searchdevelopers.godev.domain.Service> service = serviceRepository.findById(idService);

        userService.setService(service.get());
        userService.setUserDev(user.get());
        userService.getUserDev().setStarsUser(
                validateRating(userService.getUserDev().getRatingsCont(),
                        userService.getUserDev().getRatingsSum(),
                        userService.getService().getStarts()));
        userServiceRepository.save(userService);

        return userService;
    }

//        UserService serviceFind = userServiceRepository
//                .findByServiceIdServiceAndUserDevIdUser(idService, idUserDev);
//        UserService serviceRating = new UserService();
//
//        serviceRating.setIdUserService(serviceFind.get());
//        repository.save(formation);
//
//        if (userServiceRepository.existsByServiceIdService(idService)){
//
//            return ResponseEntity.ok(repository.findById(id));
//        } else{
//            return ResponseEntity.badRequest().build();
//        }
//
//    }

    public Double validateRating (Integer ratingsCont, Double ratingsSum, Double starsService){
        ratingsCont++;
        ratingsSum += starsService;
        Double total = ratingsSum / ratingsCont;
        return total;
    }

}
