package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    boolean existsByUserDevIdUserAndUserDevRoleEquals (Integer idUser, String role);
    boolean existsByUserCltIdUserAndUserCltRoleEquals (Integer idUser, String role);

    List<Favorite> findByUserCltIdUserAndFavoriteTrue(Integer idUserClt);
    List<Favorite> findByUserDevIdUserAndFavoriteTrue(Integer idUserDev);
}
