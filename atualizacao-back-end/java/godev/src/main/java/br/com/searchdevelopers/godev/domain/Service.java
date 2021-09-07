package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String descriptionService;

    private Integer progress;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDateService;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDateService;

    private Boolean activeService;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String tag;

    @Size(max = 200)
    private String descriptionRating;

    private Double starts;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<UserService> userServices;

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

    public LocalDate getStartDateService() {
        return startDateService;
    }

    public LocalDate getStartDateServiceCharSequence(){return startDateService;}

    public void setStartDateService(LocalDate startDateService) {
        this.startDateService = startDateService;
    }

    public LocalDate getEndDateService() {
        return endDateService;
    }

    public void setEndDateService(LocalDate endDateService) {
        this.endDateService = endDateService;
    }

    public List<UserService> getUserServices() {
        return userServices;
    }

    public void setUserServices(List<UserService> userServices) {
        this.userServices = userServices;
    }
}
