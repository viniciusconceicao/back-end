package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {



  List<Notification> findAllByUserRIdUser(Integer idR);

  List<Notification> findAllByUserSIdUser(Integer idS);

}
