package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {

    boolean existsByEmail(String email);
    boolean existsByIdUserAndRoleEquals (Integer id, String role);

    Users findUserByIdUser (Integer id);

    Optional<Users> findByEmail(String email);

    List<Users> findByRoleEquals(String typeUser);
}
