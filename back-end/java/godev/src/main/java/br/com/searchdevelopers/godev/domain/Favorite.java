package br.com.searchdevelopers.godev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavorites;

    private Integer fkIdUserDev;

    private Integer fkIdUserCl;

    private Boolean favorite;

}
