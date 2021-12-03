package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    boolean existsByUsersDevIdUserAndUsersDevRoleEquals (Integer idUser, String role);
    boolean existsByUsersCltIdUserAndUsersCltRoleEquals (Integer idUser, String role);
    boolean existsByFavoriteTrue();
//    boolean existsByIdFavoriteNotNull();
//    boolean existsByUsersCltIdUserAndUsersDevIdUser (Integer idUserClt, Integer idUserDev);

    List<Favorite> findByUsersCltIdUserAndFavoriteTrue(Integer idUserClt);
    List<Favorite> findByUsersDevIdUserAndFavoriteTrue(Integer idUserDev);
    List<Favorite> findByUsersCltIdUserAndUsersDevIdUser (Integer idUserClt, Integer idUserDev);
}
