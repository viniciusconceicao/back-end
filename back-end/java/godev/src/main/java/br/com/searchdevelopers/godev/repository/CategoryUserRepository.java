package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.CategoryUser;
import br.com.searchdevelopers.godev.domain.CategoryUserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryUserRepository extends CrudRepository<CategoryUser, Integer> {
    boolean existsByUsersIdUserAndTypeEquals(Integer idUser, CategoryUserType type);

    List<CategoryUser> findByUsersIdUser(Integer idUser);

}
