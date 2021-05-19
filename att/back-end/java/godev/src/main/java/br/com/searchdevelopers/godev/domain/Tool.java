package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTool;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String nameTool;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Integer getIdTool() {
        return idTool;
    }

    public void setIdTool(Integer idTool) {
        this.idTool = idTool;
    }

    public String getNameTool() {
        return nameTool;
    }

    public void setNameTool(String nameTool) {
        this.nameTool = nameTool;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
