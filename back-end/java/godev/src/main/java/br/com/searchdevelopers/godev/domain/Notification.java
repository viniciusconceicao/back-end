package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotification;

    @NotNull
    @Size(min = 2, max = 45)
    private String typeNotification;

    @NotNull
    @Size(min = 2, max = 45)
    private String title;

    @NotNull
    private  Boolean statusNotification;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "fk_user_sender")
    private Users usersSender;

    @ManyToOne
    @JoinColumn(name = "fk_user_receiver")
    private Users usersReceiver;

    public Notification(String typeNotification, String title, Boolean statusNotification, Users usersSender, Users usersReceiver) {
        this.typeNotification = typeNotification;
        this.title = title;
        this.statusNotification = statusNotification;
        this.usersSender = usersSender;
        this.usersReceiver = usersReceiver;
    }

    public Notification() {
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public Boolean getStatusNotification() {
        return statusNotification;
    }

    public void setStatusNotification(Boolean statusNotification) {
        this.statusNotification = statusNotification;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String type) {
        this.typeNotification = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getUserSender() {
        return usersSender;
    }

    public void setUserSender(Users usersSender) {
        this.usersSender = usersSender;
    }

    public Users getUserReceiver() {
        return usersReceiver;
    }

    public void setUserReceiver(Users usersReceiver) {
        this.usersReceiver = usersReceiver;
    }
}
