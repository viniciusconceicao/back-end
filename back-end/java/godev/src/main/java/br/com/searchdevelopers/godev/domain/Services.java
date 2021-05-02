package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String descriptionService;

    @Size(max = 4)
    private Integer progress;

    private Boolean activeService;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String tag;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String specially;

    @Size(max = 200)
    private String descriptionRating;

    private Double starts;

    @ManyToMany(mappedBy = "services")
    private List<Users> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return Objects.equals(idService, services.idService) && Objects.equals(descriptionService, services.descriptionService) && Objects.equals(progress, services.progress) && Objects.equals(activeService, services.activeService) && Objects.equals(tag, services.tag) && Objects.equals(specially, services.specially) && Objects.equals(descriptionRating, services.descriptionRating) && Objects.equals(starts, services.starts) && Objects.equals(users, services.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idService, descriptionService, progress, activeService, tag, specially, descriptionRating, starts, users);
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Boolean getActiveService() {
        return activeService;
    }

    public void setActiveService(Boolean activeService) {
        this.activeService = activeService;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSpecially() {
        return specially;
    }

    public void setSpecially(String specially) {
        this.specially = specially;
    }

    public String getDescriptionRating() {
        return descriptionRating;
    }

    public void setDescriptionRating(String descriptionRating) {
        this.descriptionRating = descriptionRating;
    }

    public Double getStarts() {
        return starts;
    }

    public void setStarts(Double starts) {
        this.starts = starts;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
