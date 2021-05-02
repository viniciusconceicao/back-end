package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    boolean existsByEmail(String email);

    @Override
    List<Users> findAll();

    Optional<Users> findByEmail(String email);

    List<Users> findByRoleContaining(String typeUser);

}
