package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Favorite;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.FavoriteRepository;
import br.com.searchdevelopers.godev.usecases.RegisterFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository repository;

    private final RegisterFavorite registerFavorite;


    public FavoriteController(RegisterFavorite registerFavorite) {
        this.registerFavorite = registerFavorite;
    }

    @PostMapping("/client/{idClt}/dev/{idDev}")
    public ResponseEntity postFavorite(@Valid @PathVariable Integer idClt,
                                       @Valid @PathVariable Integer idDev) {
        try{
            registerFavorite.saveFavorite(idClt,idDev);
            return ResponseEntity.created(null).build();
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getTypeUsers(@PathVariable int id){

        List<Favorite> devs = repository.findByUsersDevIdUserAndFavoriteTrue(id);
        List<Favorite> clients = repository.findByUsersCltIdUserAndFavoriteTrue(id);

        boolean isDev = repository.existsByUsersDevIdUserAndUsersDevRoleEquals(id, "dev");
        boolean isClt = repository.existsByUsersCltIdUserAndUsersCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.status(200).body(clients);
        } else if (isClt){
            return ResponseEntity.status(200).body(devs);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdFavorite(@PathVariable Integer id) {
        if(repository.findById(id).isEmpty()){
            return ResponseEntity.noContent().build();
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

//        Users sender = userRepository.getOne(1);
//
//        NotificationController notificationController = new NotificationController();
//
//        Notification notification = new Notification("Perfil favoritado",
//                "Parabens, seu perfil acabou de ser favoritado", true,sender,userRepository.)

        if (repository.existsById(id)) {
            favorite.setIdFavorite(id);
            repository.save(favorite);
            //notificationController.postNotification()
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
