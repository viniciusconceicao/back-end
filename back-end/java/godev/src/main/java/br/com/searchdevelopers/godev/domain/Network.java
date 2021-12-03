package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNetwork;

    @Size(max = 100)
    private String facebookName;

    @Size(max = 500)
    private String facebookUrl;

    @Size(max = 100)
    private String instagramName;

    @Size(max = 500)
    private String instagramUrl;

    @Size(max = 100)
    private String linkedinName;

    @Size(max = 500)
    private String linkedinUrl;

    @Size(max = 100)
    private String githubName;

    @Size(max = 500)
    private String githubUrl;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Integer getIdNetwork() {
        return idNetwork;
    }

    public void setIdNetwork(Integer idNetwork) {
        this.idNetwork = idNetwork;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramName() {
        return instagramName;
    }

    public void setInstagramName(String instagramName) {
        this.instagramName = instagramName;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getLinkedinName() {
        return linkedinName;
    }

    public void setLinkedinName(String linkedinName) {
        this.linkedinName = linkedinName;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getGithubName() {
        return githubName;
    }

    public void setGithubName(String githubName) {
        this.githubName = githubName;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
