package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  private NotificationRepository repository;

  @PostMapping("/{idS}/{idR}")
  public ResponseEntity postNotification(@Valid @PathVariable User idS,
                                         @Valid @PathVariable User idR,
                                         @RequestBody Notification notification) {
    try {
      notification.setUserS(idS);
      notification.setUserR(idR);
      repository.save(notification);
      return ResponseEntity.ok().build();
    } catch (Exception erro) {
      return  ResponseEntity.badRequest().build();
    }
  }

  @GetMapping
  public ResponseEntity getAllNotifications() {
    if (repository.findAll().isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAll());
    }
  }

  @GetMapping("{idR}")
  public ResponseEntity getAllNotificationsToUserR(@Valid @PathVariable Integer idR) {
    if (repository.findAllByUserRIdUser(idR).isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAllByUserRIdUser(idR));
    }
  }

  @GetMapping("{idS}")
  public ResponseEntity getAllNotificationsByUserR(@Valid @PathVariable Integer idS) {
    if (repository.findAllByUserRIdUser(idS).isEmpty()){
      return ResponseEntity.status(204).build();
    } else {
      return ResponseEntity.ok(repository.findAllByUserRIdUser(idS));
    }
  }

  @DeleteMapping("/{idN}")
  public ResponseEntity deleteNotificationToUserR( @PathVariable Integer idN) {
    if (repository.existsById(idN)) {
        repository.deleteById(idN);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.noContent().build();
    }
  }

}
