package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormation;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String nameInstitution;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String course;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String languageFormation;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDateFormation;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDateFormation;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Formation(String nameInstitution, String course, String languageFormation, LocalDate startDateFormation, LocalDate endDateFormation) {
        this.nameInstitution = nameInstitution;
        this.course = course;
        this.languageFormation = languageFormation;
        this.startDateFormation = startDateFormation;
        this.endDateFormation = endDateFormation;
    }

    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public String getNameInstitution() {
        return nameInstitution;
    }

    public void setNameInstitution(String nameInstitution) {
        this.nameInstitution = nameInstitution;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLanguageFormation() {
        return languageFormation;
    }

    public void setLanguageFormation(String languageFormation) {
        this.languageFormation = languageFormation;
    }

    public LocalDate getStartDateFormation() {
        return startDateFormation;
    }

    public void setStartDateFormation(LocalDate startDateFormation) {
        this.startDateFormation = startDateFormation;
    }

    public LocalDate getEndDateFormation() {
        return endDateFormation;
    }

    public void setEndDateFormation(LocalDate endDateFormation) {
        this.endDateFormation = endDateFormation;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
