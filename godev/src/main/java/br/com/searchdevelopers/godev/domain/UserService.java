package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;

@Entity
public class UserService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserService;

    @ManyToOne
    @JoinColumn(name = "users_dev_id")
    private Users usersDev;

    @ManyToOne
    @JoinColumn(name = "users_clt_id")
    private Users usersClt;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Integer getIdUserService() {
        return idUserService;
    }

    public void setIdUserService(Integer idUserService) {
        this.idUserService = idUserService;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
