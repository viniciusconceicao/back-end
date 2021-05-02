package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Integer> {
}
