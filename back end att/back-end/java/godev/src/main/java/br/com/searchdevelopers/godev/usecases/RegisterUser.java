package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class RegisterUser {

    private UserRepository repository;

    @Autowired
    public RegisterUser(UserRepository repository) {
        this.repository = repository;
    }

    public User authenticate (String email, String password){
        Optional<User> users = repository.findByEmail(email);
        if(!users.isPresent()){
            throw new AuthenticationErrorException("Usuário não encontrado");
        }
        if(!users.get().getPassword().equals(password)){
            throw new AuthenticationErrorException("Senha inválida");
        }
        return users.get();
    }

    @Transactional
    public User saveUser(User user){
        validateEmail(user.getEmail());
        user.setStars(0.0);
        user.setXp(0);
        user.setStatus(true);
        return repository.save(user);
    }

    public void validateEmail(String email){
        boolean exist = repository.existsByEmail(email);
        if(exist){
            throw new BusinessRuleException("Já existe um usuário cadastrado com esse email");
        }
    }

    public Optional<User> findByIdUser (Integer id) {
        if(repository.findById(id).isEmpty()){
            throw new SearchErrorException("Usuário não encontrado com esse id informado.");
        }
        return repository.findById(id);
    }
}
