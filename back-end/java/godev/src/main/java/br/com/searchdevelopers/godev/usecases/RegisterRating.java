package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterRating {

    private UserRepository userRepository;
    private UserServiceRepository userServiceRepository;

    @Autowired
    public RegisterRating(UserRepository userRepository, UserServiceRepository userServiceRepository) {
        this.userRepository = userRepository;
        this.userServiceRepository = userServiceRepository;
    }

//    public void starRating(Integer idService,
//                           Integer idUserDev) {
//        UserService services = userServiceRepository
//                .findByServiceIdServiceAndUsersDevIdUser(idService, idUserDev);
//        services.set(users.get());
//        repository.save(formation);
//
//        if (userServiceRepository.existsByServiceIdService(idService)){
//
//            return ResponseEntity.ok(repository.findById(id));
//        } else{
//            return ResponseEntity.badRequest().build();
//        }
//
//        if (userServiceRepository.existsByServiceIdServiceAndUserDevIdUser(idService,idUserDev)) {
//            user.setIdUser(idUserDev);
//            validateRating(user.getRatingsCont(), user.getRatingsSum(), service.getStarts(), user.getStarsUser());
//            userRepository.save(user);
//        }
//    }

    public Double validateRating (Integer ratingsCont, Double ratingsSum, Double starsService, Double starsUser){
        ratingsCont++;
        ratingsSum += starsService;
        starsUser = ratingsSum / ratingsCont;
        return starsUser;
    }

}
