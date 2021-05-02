package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Experience() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(idExperience, that.idExperience) && Objects.equals(nameCompany, that.nameCompany) && Objects.equals(startDateExperience, that.startDateExperience) && Objects.equals(endDateExperience, that.endDateExperience) && Objects.equals(descriptionExperience, that.descriptionExperience) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExperience, nameCompany, startDateExperience, endDateExperience, descriptionExperience, users);
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
