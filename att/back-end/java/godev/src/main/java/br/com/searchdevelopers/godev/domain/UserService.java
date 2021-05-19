package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;

@Entity
public class UserService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserService;

    @ManyToOne
    @JoinColumn(name = "users_dev_id")
    private User userDev;

    @ManyToOne
    @JoinColumn(name = "users_clt_id")
    private User userClt;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Integer getIdUserService() {
        return idUserService;
    }

    public void setIdUserService(Integer idUserService) {
        this.idUserService = idUserService;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
