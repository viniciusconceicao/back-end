package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExperience;

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String nameCompany;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDateExperience;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDateExperience;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String descriptionExperience;

    public Experience(String nameCompany, LocalDate startDateExperience, LocalDate endDateExperience, String descriptionExperience) {
        this.nameCompany = nameCompany;
        this.startDateExperience = startDateExperience;
        this.endDateExperience = endDateExperience;
        this.descriptionExperience = descriptionExperience;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Experience() {
    }

    public Integer getIdExperience() {
        return idExperience;
    }

    public void setIdExperience(Integer idExperience) {
        this.idExperience = idExperience;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public LocalDate getStartDateExperience() {
        return startDateExperience;
    }

    public void setStartDateExperience(LocalDate startDateExperience) {
        this.startDateExperience = startDateExperience;
    }

    public LocalDate getEndDateExperience() {
        return endDateExperience;
    }

    public void setEndDateExperience(LocalDate endDateExperience) {
        this.endDateExperience = endDateExperience;
    }

    public String getDescriptionExperience() {
        return descriptionExperience;
    }

    public void setDescriptionExperience(String descriptionExperience) {
        this.descriptionExperience = descriptionExperience;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
