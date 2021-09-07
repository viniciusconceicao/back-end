package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.usecases.fila.FilaObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

//    FilaObj<Service> findFirstByIdService();

}
