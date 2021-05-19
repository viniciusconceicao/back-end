package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNetwork;

    @Size(max = 200)
    private String facebook;

    @Size(max = 200)
    private String instagram;

    @Size(max = 200)
    private String linkedin;

    @Size(max = 200)
    private String github;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Integer getIdNetwork() {
        return idNetwork;
    }

    public void setIdNetwork(Integer idNetwork) {
        this.idNetwork = idNetwork;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
