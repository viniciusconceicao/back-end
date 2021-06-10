package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.usecases.pilha.PilhaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

  List<Notification> findAllByUsersReceiverIdUser(Integer idR);

  List<Notification> findNotificationByUsersReceiverIdUserOrderByDateCreatedDesc(Integer id);

  //@Query(value = "SELECT top 30 FROM NOTIFICATION ORDER BY DATE_CREATED", nativeQuery = true)
  //List<Notification> orderNotification();

  List<Notification> findNotificationByUsersReceiverIdUser(Integer idReceiver);

  List<Notification> findNotificationByUsersSenderIdUser(Integer idSender);

//  @Query("SELECT COUNT(n.idNotification) FROM Notification n where n.userR = ?1")
//  Integer contarRegistros(Integer idUserR);



}
