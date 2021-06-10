package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
