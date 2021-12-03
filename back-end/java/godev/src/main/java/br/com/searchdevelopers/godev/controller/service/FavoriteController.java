package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Favorite;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
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
    private FavoriteRepository favoriteRepository;

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
        } catch (AuthenticationErrorException e) {
            return ResponseEntity.status(401).body("Usuário Cliente ou Desenvolvedor com id errado.");
        }
    }

    @PutMapping("/dislike/client/{idClt}/dev/{idDev}")
    public ResponseEntity authenticate(@Valid @PathVariable Integer idClt,
                                       @Valid @PathVariable Integer idDev) {
        try {
            registerFavorite.dislikeFavorite(idClt, idDev);
            return ResponseEntity.created(null).build();
        } catch (AuthenticationErrorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getTypeUsers(@PathVariable int id){

        List<Favorite> devs = favoriteRepository.findByUsersDevIdUserAndFavoriteTrue(id);
        List<Favorite> clients = favoriteRepository.findByUsersCltIdUserAndFavoriteTrue(id);

        boolean isDev = favoriteRepository.existsByUsersDevIdUserAndUsersDevRoleEquals(id, "dev");
        boolean isClt = favoriteRepository.existsByUsersCltIdUserAndUsersCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.status(200).body(devs);
        } else if (isClt){
            return ResponseEntity.status(200).body(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdFavorite(@PathVariable Integer id) {
        if(favoriteRepository.findById(id).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoriteRepository.findById(id));
    }

    @GetMapping(path = "/")
    public ResponseEntity findAllFavorites() {
        if (favoriteRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(favoriteRepository.findAll());
        }
    }

}
