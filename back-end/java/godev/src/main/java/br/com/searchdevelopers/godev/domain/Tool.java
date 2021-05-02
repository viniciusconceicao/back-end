package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

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
    private Users users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tool tool = (Tool) o;
        return Objects.equals(idTool, tool.idTool) && Objects.equals(nameTool, tool.nameTool) && Objects.equals(users, tool.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTool, nameTool, users);
    }

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
