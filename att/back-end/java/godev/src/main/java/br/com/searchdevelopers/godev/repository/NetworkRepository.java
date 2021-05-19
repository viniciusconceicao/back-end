package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Formation;
import br.com.searchdevelopers.godev.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NetworkRepository extends JpaRepository<Network, Integer> {
    Formation findByUserIdUser(Integer idUser);
}
