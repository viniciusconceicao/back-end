package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.dto.PhotoUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {

    boolean existsByEmail(String email);
    boolean existsByIdUserAndRoleEquals (Integer id, String role);

    Optional<Users> findByEmail(String email);

    List<Users> findByRoleEquals(String typeUser);

    boolean existsByIdUser(Integer id);

    Users findByIdUser(Integer id);

    @Query("SELECT u FROM Users AS u")
    PhotoUserDto findByPhoto();

}
