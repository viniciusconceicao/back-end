package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Integer> {

    List<Formation> findByUsersIdUser(Integer idUser);
}
