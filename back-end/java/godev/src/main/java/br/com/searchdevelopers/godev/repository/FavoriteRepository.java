package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
}
