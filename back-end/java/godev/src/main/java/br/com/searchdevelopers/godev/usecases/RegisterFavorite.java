package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Favorite;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterFavorite {

    private FavoriteRepository repository;
    private final RegisterUser registerUser;

    @Autowired
    public RegisterFavorite(FavoriteRepository repository, RegisterUser registerUser) {
        this.repository = repository;
        this.registerUser = registerUser;
    }

    public void saveFavorite(Integer idClt, Integer idDev){
        Favorite favorite = new Favorite();
        Optional<Users> userClt = registerUser.findByIdUser(idClt);
        Optional<Users> userDev = registerUser.findByIdUser(idDev);

        if (userClt.get().getRole().equals("clt") && userDev.get().getRole().equals("dev")){
            favorite.setUserClt(userClt.get());
            favorite.setUserDev(userDev.get());
            validateFavoriteUser(favorite.getUserClt().getIdUser(), favorite.getUserDev().getIdUser());
            favorite.setFavorite(true);
            repository.save(favorite);
        } else {
            throw new AuthenticationErrorException("Usuário Cliente ou Desenvolvedor com id errado.");
        }
    }

    public Favorite dislikeFavorite(Integer idClt, Integer idDev) {
        Optional<Favorite> favorite = repository.findByUsersCltIdUserAndUsersDevIdUser(idClt, idDev).stream().findFirst();
        if(!favorite.isPresent()){
            throw new BusinessRuleException("Usuário não favoritado");
        }
        repository.delete(favorite.get());
        return favorite.get();
    }

    public void validateFavoriteUser(Integer idUserClt, Integer idUserDev){
        boolean existDev = repository.existsByUsersDevIdUserAndUsersDevRoleEquals(idUserDev, "dev");
        boolean existClt = repository.existsByUsersCltIdUserAndUsersCltRoleEquals(idUserClt, "clt");
        if(existDev && existClt){
            throw new BusinessRuleException("Esse usuário já foi favoritado.");
        }
    }
}
