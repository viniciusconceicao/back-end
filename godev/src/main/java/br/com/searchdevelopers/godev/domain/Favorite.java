package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavorite;

    @ManyToOne
    @JoinColumn(name = "users_dev_id")
    private Users usersDev;

    @ManyToOne
    @JoinColumn(name = "users_clt_id")
    private Users usersClt;

    private Boolean favorite;

    public String getUserCltStringRole(){
        return usersClt.getRole();
    }

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Users getUserDev() {
        return usersDev;
    }

    public void setUserDev(Users usersDev) {
        this.usersDev = usersDev;
    }

    public Users getUserClt() {
        return usersClt;
    }

    public void setUserClt(Users usersClt) {
        this.usersClt = usersClt;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
