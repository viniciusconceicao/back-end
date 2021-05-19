package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class RegisterService {

    private ServiceRepository repository;

    public RegisterService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Service save(Service service) {
        service.setActiveService(false);
        service.setStarts(0.0);
        service.setProgress(0);
        return repository.save(service);
    }
}
