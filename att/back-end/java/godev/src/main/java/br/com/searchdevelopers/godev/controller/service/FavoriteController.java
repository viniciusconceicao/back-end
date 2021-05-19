package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Favorite;
import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.FavoriteRepository;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository repository;

    private final RegisterUser registerUser;

    public FavoriteController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/client/{idClt}/dev/{idDev}")
    public ResponseEntity postFavorite(@Valid @PathVariable Integer idClt,
                                       @Valid @PathVariable Integer idDev) {
        try{
            Favorite favorite = new Favorite();
            Optional<User> userClt = registerUser.findByIdUser(idClt);
            Optional<User> userDev = registerUser.findByIdUser(idDev);

            favorite.setUserClt(userClt.get());
            favorite.setUserDev(userDev.get());
            favorite.setFavorite(true);
            repository.save(favorite);

            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getTypeUsers(@PathVariable int id){

        List<Favorite> devs = repository.findByUserDevIdUserAndFavoriteTrue(id);
        List<Favorite> clients = repository.findByUserCltIdUserAndFavoriteTrue(id);

        boolean isDev = repository.existsByUserDevIdUserAndUserDevRoleEquals(id, "dev");
        boolean isClt = repository.existsByUserCltIdUserAndUserCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.ok(devs);
        } else if (isClt){
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdFavorite(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping(path = "/")
    public ResponseEntity findAllFavorites() {
        if (repository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putFavorite(@Valid @PathVariable Integer id, @RequestBody Favorite favorite) {
        if (repository.existsById(id)) {
            favorite.setIdFavorite(id);
            repository.save(favorite);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteFavorite(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
