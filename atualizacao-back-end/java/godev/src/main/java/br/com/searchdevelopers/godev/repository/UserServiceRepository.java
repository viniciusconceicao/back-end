package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServiceRepository extends JpaRepository<UserService,Integer> {

    boolean existsByUsersDevIdUserAndUsersDevRoleEquals (Integer idUser, String role);
    boolean existsByUsersCltIdUserAndUsersCltRoleEquals (Integer idUser, String role);
//    boolean existsByIdUserServiceAndUserDevIdUser (Integer idService, Integer idUserDev);
//    boolean existsByUserDevIdUser (Integer idUserDev);
//
//    UserService findByUserDevIdUser(Integer idUserDev);

    List<UserService> findByUsersDevIdUserAndServiceIsNotNull(Integer idUserDev);

    UserService findByServiceIdServiceAndUsersCltIdUser(Integer idService, Integer idUserClt);
    UserService findByServiceIdServiceAndUsersDevIdUser(Integer idService, Integer idUserDev);

    List<UserService> findByUsersCltIdUserAndServiceActiveServiceTrue(Integer idUserClt);
    List<UserService> findByUsersDevIdUserAndServiceActiveServiceTrue(Integer idUserDev);
    List<UserService> findByServiceActiveServiceTrue();

    List<UserService> findByUsersCltIdUserAndServiceActiveServiceFalseAndServiceProgress(Integer idUserClt, Integer progress);
    List<UserService> findByUsersDevIdUserAndServiceActiveServiceFalseAndServiceProgress(Integer idUserDev, Integer progress);
    List<UserService> findByServiceActiveServiceFalseAndServiceProgress(Integer progress);


    //List<UserService> findAllUsersCltIdUserAndServiceActiveServiceFalse();

    //FilaObj<UserService> findAllByIdUserServiceOrderByIdUserService();


//    select * from UserService where idUserService
    //List<UserService> findByIdUserService
}
