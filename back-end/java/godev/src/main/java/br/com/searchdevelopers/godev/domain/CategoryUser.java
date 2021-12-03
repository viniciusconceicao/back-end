package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryUserType type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public CategoryUser() {
    }

    public CategoryUser(Integer idCategory,
                        CategoryUserType type,
                        Users users) {
        this.idCategory = idCategory;
        this.type = type;
        this.users = users;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public CategoryUserType getType() {
        return type;
    }

    public void setType(CategoryUserType type) {
        this.type = type;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
