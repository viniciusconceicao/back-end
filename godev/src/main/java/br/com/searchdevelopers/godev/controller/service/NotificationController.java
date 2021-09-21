package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.repository.NotificationRepository;
import br.com.searchdevelopers.godev.usecases.NotificationService;
import br.com.searchdevelopers.godev.usecases.pilha.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  private NotificationRepository repository;

  @Autowired
  private NotificationService notificationService;


  @GetMapping("/order/{id}")
  public ResponseEntity orderNotification(@PathVariable Integer id) {

    if (!repository.existsById(id)){
      return ResponseEntity.status(204).build();
    }
    else {
      return ResponseEntity.ok(repository.findNotificationByUsersReceiverIdUserOrderByDateCreatedDesc(id));
    }

  }

  @PostMapping("/sender/{idSender}/receiver/{idReceiver}")
  public ResponseEntity postNotification(@Valid @PathVariable Users idSender,
                                         @Valid @PathVariable Users idReceiver,
                                         @RequestBody Notification notification) {
    try {
      notification.setUsersSender(idSender);
      notification.setUsersReceiver(idReceiver);
      repository.save(notification);
      return ResponseEntity.ok().build();
    } catch (Exception erro) {
      return  ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/")
  public ResponseEntity getAllNotifications() {
    if (repository.findAll().isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAll());
    }
  }

  @GetMapping("{idReceiver}")
  public ResponseEntity getAllNotificationsToUserReceiver(@Valid @PathVariable Integer idReceiver) {
    if (repository.findAllByUsersReceiverIdUser(idReceiver).isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAllByUsersReceiverIdUser(idReceiver));
    }
  }

  @GetMapping("{idSender}")
  public ResponseEntity getAllNotificationsByUserReceiver(@Valid @PathVariable Integer idSender) {
    if (repository.findAllByUsersReceiverIdUser(idSender).isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAllByUsersReceiverIdUser(idSender));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteNotificationToUserR( @PathVariable Integer id) {
    if (repository.existsById(id)) {
        repository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

}
