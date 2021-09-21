package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String nameUser;

    @CPF
    @Size(max = 14)
    private String cpf;

    @CNPJ
    @Size(max = 18)
    private String cnpj;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Size(max = 45)
    private String nameCompany;

    @NotNull
    @Email
    @Size(max = 45)
    private String email;

    @NotNull
    @Size(max = 45)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 5)
    private String role;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phone;

    @Size(max = 200)
    private String descriptionUser;

    private Double starsUser;

    private Integer ratingsCont;

    private Double ratingsSum;

    private Integer xp;

    private Boolean status;

    @Size(max = 100)
    private String locality;

    @Column(length = 1_000_000_000)
    private byte[] photo;

    private String namePhoto;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    public Users() {
    }

    //Construtor usado para testar a exportação
    public Users(String nameUser,
                 String cpf,
                 String cnpj,
                 String email,
                 String role,
                 String phone) {
        this.nameUser = nameUser;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescriptionUser() {
        return descriptionUser;
    }

    public void setDescriptionUser(String descriptionUser) {
        this.descriptionUser = descriptionUser;
    }

    public Double getStarsUser() {
        return starsUser;
    }

    public void setStarsUser(Double starsUser) {
        this.starsUser = starsUser;
    }

    public Integer getRatingsCont() {
        return ratingsCont;
    }

    public void setRatingsCont(Integer ratingsCont) {
        this.ratingsCont = ratingsCont;
    }

    public Double getRatingsSum() {
        return ratingsSum;
    }

    public void setRatingsSum(Double ratingsSum) {
        this.ratingsSum = ratingsSum;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }
}