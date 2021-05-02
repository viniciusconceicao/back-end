package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class RegisterUser {

    private UsersRepository repository;

    @Autowired
    public RegisterUser(UsersRepository repository) {
        this.repository = repository;
    }

    public Users authenticate (String email, String password){
        Optional<Users> users = repository.findByEmail(email);
        if(!users.isPresent()){
            throw new AuthenticationErrorException("Usuário não encontrado");
        }
        if(!users.get().getPassword().equals(password)){
            throw new AuthenticationErrorException("Senha inválida");
        }
        return users.get();
    }

    @Transactional
    public Users saveUser(Users users){
        validateEmail(users.getEmail());
        return repository.save(users);
    }

    public void validateEmail(String email){
        boolean exist = repository.existsByEmail(email);
        if(exist){
            throw new BusinessRuleException("Já existe um usuário cadastrado com esse email");
        }
    }

    public Optional<Users> findByIdUser (Integer id) {
        if(repository.findById(id).isEmpty()){
            throw new SearchErrorException("Usuário não encontrado com esse id informado.");
        }
        return repository.findById(id);
    }
}
