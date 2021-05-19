package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
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

    private Double stars;

    private Integer xp;

    @Column(length = 5_000_000)
    private byte[] photoContent;

    private String namePhoto;

    private Boolean status;

    @Size(max = 300)
    private String locality;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Network network;

    @OneToMany(mappedBy = "userS")
    @JsonIgnore
    private List<Notification> notificationsS;

    @OneToMany(mappedBy = "userR")
    @JsonIgnore
    private List<Notification> notificationsR;

    public User() {
    }

    //Construtor usado para testar a exportação
    public User(String nameUser,
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

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public byte[] getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(byte[] photoContent) {
        this.photoContent = photoContent;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<Notification> getNotificationsS() {
        return notificationsS;
    }

    public void setNotificationsS(List<Notification> notifications) {
        this.notificationsS = notifications;
    }

    public List<Notification> getNotificationsR() {
        return notificationsR;
    }

    public void setNotificationsR(List<Notification> notificationsR) {
        this.notificationsR = notificationsR;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
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
}
