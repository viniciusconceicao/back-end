package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Services;
import br.com.searchdevelopers.godev.exceptions.SearchErrorException;
import br.com.searchdevelopers.godev.repository.ServicesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegisterServices {

    private ServicesRepository repository;

    public RegisterServices(ServicesRepository repository) {
        this.repository = repository;
    }

    public Services save(Services services) {
        services.setActiveService(null);
        return repository.save(services);
    }

    public Services update(Services services) {
        Objects.requireNonNull(services.getIdService());
        return repository.save(services);
    }

    public void deletar(Services services) {
        Objects.requireNonNull(services.getIdService());
        repository.delete(services);
    }

    public List<Services> search (Services servicesFiltro){
        Example example = Example.of(servicesFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    public void updateService(Services services){
        services.setActiveService(true);
        update(services);
    }

    public Optional<Services> findByIdService (Integer id){
        if(repository.findById(id).isEmpty()){
            throw new SearchErrorException("Services não encontrado com esse id informado.");
        }
        return repository.findById(id);
    }
}
