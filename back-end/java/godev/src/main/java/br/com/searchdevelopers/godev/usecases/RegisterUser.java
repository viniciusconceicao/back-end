package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RegisterUser {

    private UserRepository repository;

    @Autowired
    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public Users authenticate(String email, String password) {
        Optional<Users> user = repository.findByEmail(email);
        if (!user.isPresent()) {
            throw new AuthenticationErrorException("Usuário não encontrado");
        }
        if (!user.get().getPassword().equals(password)) {
            throw new AuthenticationErrorException("Senha inválida");
        }
        return user.get();
    }

    public Users saveUser(Users users) {
        validateEmail(users.getEmail());
        users.setStarsUser(0.0);
        users.setRatingsCont(0);
        users.setRatingsSum(0.0);
        users.setXp(0);
        users.setStatus(true);
        return repository.save(users);
    }

    private void validateEmail (String email){
        boolean exist = repository.existsByEmail(email);
        if (exist) {
            throw new BusinessRuleException("Já existe um usuário cadastrado com esse email");
        }
    }

    public Optional<Users> findByIdUser (Integer id){
        if (repository.findById(id).isEmpty()) {
            throw new SearchErrorException("Usuário não encontrado com esse id informado.");
        }
        return repository.findById(id);
    }
}

