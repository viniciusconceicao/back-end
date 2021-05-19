package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService,Integer> {

    boolean existsByUserDevIdUserAndUserDevRoleEquals (Integer idUser, String role);
    boolean existsByUserCltIdUserAndUserCltRoleEquals (Integer idUser, String role);

    List<UserService> findByUserCltIdUserAndServiceActiveServiceTrue(Integer idUserClt);
    List<UserService> findByUserDevIdUserAndServiceActiveServiceTrue(Integer idUserDev);
    List<UserService> findByServiceActiveServiceTrue();

    List<UserService> findByUserCltIdUserAndServiceActiveServiceFalse(Integer idUserClt);
    List<UserService> findByUserDevIdUserAndServiceActiveServiceFalse(Integer idUserDev);
    List<UserService> findByServiceActiveServiceFalse();

    List<UserService> findByUserCltIdUserAndServiceActiveServiceFalseAndServiceProgress(Integer idUserClt, Integer progress);
    List<UserService> findByUserDevIdUserAndServiceActiveServiceFalseAndServiceProgress(Integer idUserDev, Integer progress);
    List<UserService> findByServiceActiveServiceFalseAndServiceProgress(Integer progress);
}
