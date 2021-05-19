package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    @JoinColumn(name = "fk_user_s")
    private  User userS;

    @ManyToOne
    @JoinColumn(name = "fk_user_r")
    private  User userR;


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

    public User getUserS() {
        return userS;
    }

    public void setUserS(User user) {
        this.userS = user;
    }

    public User getUserR() {
        return userR;
    }

    public void setUserR(User userR) {
        this.userR = userR;
    }
}
