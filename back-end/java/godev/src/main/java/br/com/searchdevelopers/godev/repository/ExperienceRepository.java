package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Integer> {

    List<Experience> findByUsersIdUser(Integer idUser);
}
