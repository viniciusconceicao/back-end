package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

  List<CreditCard> findByUsersIdUser(Integer idUser);

}
