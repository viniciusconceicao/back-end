package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
