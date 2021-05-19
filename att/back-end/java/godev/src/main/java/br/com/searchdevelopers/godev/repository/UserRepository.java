package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    boolean existsByIdUserAndRoleEquals (Integer id, String role);

    Optional<User> findByEmail(String email);

    List<User> findByRoleEquals(String typeUser);

}
