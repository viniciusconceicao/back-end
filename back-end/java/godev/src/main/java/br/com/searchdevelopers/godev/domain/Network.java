package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

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
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(idNetwork, network.idNetwork) && Objects.equals(facebook, network.facebook) && Objects.equals(instagram, network.instagram) && Objects.equals(linkedin, network.linkedin) && Objects.equals(github, network.github) && Objects.equals(user, network.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNetwork, facebook, instagram, linkedin, github, user);
    }

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
