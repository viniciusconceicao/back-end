package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.CategoryUser;
import br.com.searchdevelopers.godev.domain.CategoryUserType;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.CategoryUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryUserService {

    private CategoryUserRepository categoryUserRepository;
    private RegisterUser registerUser;

    public CategoryUserService(CategoryUserRepository categoryUserRepository, RegisterUser registerUser) {
        this.categoryUserRepository = categoryUserRepository;
        this.registerUser = registerUser;
    }

    public ResponseEntity getUserIdCategory(Integer idUser) {
        List<CategoryUser> categoryUser = categoryUserRepository.findByUsersIdUser(idUser);
        if(!categoryUser.isEmpty()) {
            return ResponseEntity.ok(categoryUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public void saveCategoryUser (Integer idUser, CategoryUser categoryUser){
        Optional<Users> user = registerUser.findByIdUser(idUser);
        categoryUser = this.convertStringCategory(idUser, categoryUser);
        categoryUser.setUsers(user.get());

        if(categoryUser.getType() != null){
            categoryUserRepository.save(categoryUser);
        } else {
            throw new AuthenticationErrorException("Usuário com o id errado.");
        }
    }

    private CategoryUser convertStringCategory(Integer idUser, CategoryUser category) {

        boolean existCategory = this.validateCategoryUser(idUser, category.getType());
        String categoryUser = category.getType().toString();

        if(existCategory) {

            switch (categoryUser) {
                case "DESENVOLVIMENTO":
                    category.setType(CategoryUserType.DESENVOLVIMENTO);
                    break;
                case "BANCO_DADOS":
                    category.setType(CategoryUserType.BANCO_DADOS);
                    break;
                case "CLOUD_COMPUTING":
                    category.setType(CategoryUserType.CLOUD_COMPUTING);
                    break;
                case "INFRAESTRUTURA":
                    category.setType(CategoryUserType.INFRAESTRUTURA);
                    break;
                case "SEGURANCA_INFORMACAO":
                    category.setType(CategoryUserType.SEGURANCA_INFORMACAO);
                    break;
                default:
                    throw new SearchErrorException("Categoria não encontrada.");
            }
        }

        return category;
    }

    private boolean validateCategoryUser(Integer idUser, CategoryUserType type){
        if (categoryUserRepository.existsByUsersIdUserAndTypeEquals(idUser, type)){
            throw new BusinessRuleException("Esse usuário já tem categoria registrada.");
        }
        return true;
    }
}
