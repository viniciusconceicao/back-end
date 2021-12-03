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

    @NotNull (message = "startDate está nulo")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDateExperience;

    @NotNull(message = "endDate está nulo")
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDateExperience;

    @NotBlank (message = "descrição não pode estar em branco")
    @Size(max = 200)
    private String descriptionExperience;

    @NotBlank (message = "")
    @Size(max = 70)
    private String position;

    @NotBlank
    @Size(max = 70)
    private String functions;

    @Size(max = 100)
    private String locality;


    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Experience(String nameCompany, LocalDate startDateExperience, LocalDate endDateExperience, String descriptionExperience, String position, String functions, String locality) {
        this.nameCompany = nameCompany;
        this.startDateExperience = startDateExperience;
        this.endDateExperience = endDateExperience;
        this.descriptionExperience = descriptionExperience;
        this.position = position;
        this.functions = functions;
        this.locality = locality;
    }

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

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        users = users;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "idExperience=" + idExperience +
                ", nameCompany='" + nameCompany + '\'' +
                ", startDateExperience=" + startDateExperience +
                ", endDateExperience=" + endDateExperience +
                ", descriptionExperience='" + descriptionExperience + '\'' +
                ", position='" + position + '\'' +
                ", functions='" + functions + '\'' +
                ", locality='" + locality + '\'' +
                ", users=" + users +
                '}';
    }
}
