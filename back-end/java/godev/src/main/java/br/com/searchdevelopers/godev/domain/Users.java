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
import java.util.Objects;

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

    private Integer xp;

    private Byte photo;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Experience> experiences;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Formation> formations;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Language> languages;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Tool> tools;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Network network;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_service",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Services> services;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(idUser, users.idUser) && Objects.equals(nameUser, users.nameUser) && Objects.equals(cpf, users.cpf) && Objects.equals(cnpj, users.cnpj) && Objects.equals(birthDate, users.birthDate) && Objects.equals(nameCompany, users.nameCompany) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(role, users.role) && Objects.equals(phone, users.phone) && Objects.equals(descriptionUser, users.descriptionUser) && Objects.equals(xp, users.xp) && Objects.equals(photo, users.photo) && Objects.equals(creationDate, users.creationDate) && Objects.equals(experiences, users.experiences) && Objects.equals(formations, users.formations) && Objects.equals(languages, users.languages) && Objects.equals(tools, users.tools) && Objects.equals(network, users.network) && Objects.equals(services, users.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, nameUser, cpf, cnpj, birthDate, nameCompany, email, password, role, phone, descriptionUser, xp, photo, creationDate, experiences, formations, languages, tools, network, services);
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

    public Byte getPhoto() {
        return photo;
    }

    public void setPhoto(Byte photo) {
        this.photo = photo;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
}
