package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavorite;

    @ManyToOne
    @JoinColumn(name = "users_dev_id")
    private User userDev;

    @ManyToOne
    @JoinColumn(name = "users_clt_id")
    private User userClt;

    private Boolean favorite;

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public User getUserDev() {
        return userDev;
    }

    public void setUserDev(User userDev) {
        this.userDev = userDev;
    }

    public User getUserClt() {
        return userClt;
    }

    public void setUserClt(User userClt) {
        this.userClt = userClt;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
