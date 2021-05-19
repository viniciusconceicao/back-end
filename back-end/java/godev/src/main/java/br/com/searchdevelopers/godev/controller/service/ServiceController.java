package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import br.com.searchdevelopers.godev.usecases.RegisterService;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository repository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    private final RegisterService registerService;
    private final RegisterUser registerUser;

    public ServiceController(RegisterService registerService, RegisterUser registerUser) {
        this.registerService = registerService;
        this.registerUser = registerUser;
    }

    @PostMapping("/client/{idClt}/dev/{idDev}")
    public ResponseEntity postFavorite( @PathVariable Integer idClt,
                                        @PathVariable Integer idDev,
                                       @RequestBody Service service) {
        try{
            UserService userService = new UserService();
            Optional<User> userClt = registerUser.findByIdUser(idClt);
            Optional<User> userDev = registerUser.findByIdUser(idDev);

            userService.setUserClt(userClt.get());
            userService.setUserDev(userDev.get());
            userService.setService(service);

            registerService.save(service);
            userServiceRepository.save(userService);
            return ResponseEntity.created(null).build();

        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity getInactiveTypeUsers(@PathVariable int id){

        List<UserService> devs = userServiceRepository.findByUserDevIdUserAndServiceActiveServiceFalse(id);
        List<UserService> clients = userServiceRepository.findByUserCltIdUserAndServiceActiveServiceFalse(id);

        boolean isDev = userServiceRepository.existsByUserDevIdUserAndUserDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUserCltIdUserAndUserCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.ok(devs);
        } else if (isClt){
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/inactive")
    public ResponseEntity getAllInactiveServices(){
        List<UserService> inactiveServices = userServiceRepository.findByServiceActiveServiceFalse();
        if (inactiveServices.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(inactiveServices);
        }
    }

    @GetMapping("/active/{id}")
    public ResponseEntity getActiveTypeUsers(@PathVariable int id){

        List<UserService> devs = userServiceRepository.findByUserDevIdUserAndServiceActiveServiceTrue(id);
        List<UserService> clients = userServiceRepository.findByUserCltIdUserAndServiceActiveServiceTrue(id);

        boolean isDev = userServiceRepository.existsByUserDevIdUserAndUserDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUserCltIdUserAndUserCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.ok(devs);
        } else if (isClt){
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/active")
    public ResponseEntity getAllActiveServices(){
        List<UserService> activeServices = userServiceRepository.findByServiceActiveServiceTrue();
        if (activeServices.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(activeServices);
        }
    }

    @GetMapping("/finished/{id}")
    public ResponseEntity getFinishedTypeUsers(@PathVariable int id){

        Integer progressFinished = 100;
        List<UserService> devs = userServiceRepository
                .findByUserDevIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressFinished);
        List<UserService> clients = userServiceRepository
                .findByUserCltIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressFinished);

        boolean isDev = userServiceRepository.existsByUserDevIdUserAndUserDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUserCltIdUserAndUserCltRoleEquals(id, "clt");

        if (isDev){
            return ResponseEntity.ok(devs);
        } else if (isClt){
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/finished")
    public ResponseEntity getAllFinishedServices(){
        Integer finishedService = 100;
        List<UserService> finishedServices = userServiceRepository
                .findByServiceActiveServiceFalseAndServiceProgress(finishedService);
        if (finishedServices.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(finishedServices);
        }
    }

    @GetMapping
    public ResponseEntity getAllServices(){
        if (repository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(repository.findAll());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdService(@PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            return ResponseEntity.ok(repository.findById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putServices(@Valid @PathVariable Integer id, @RequestBody Service service) {
        if (repository.existsById(id)) {
            service.setIdService(id);
            repository.save(service);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity search(
//            @RequestParam(value = "descriptionService", required = false) String descriptionService,
//            @RequestParam(value = "tag", required = false) String tag,
//            @RequestParam(value = "users", required = false) Integer idUser) {
//
//        Service servicesFilter = new Service();
//        servicesFilter.setDescriptionService(descriptionService);
//        servicesFilter.setTag(tag);
//
//        Optional<User> users = registerUser.findByIdUser(idUser);
//        if(!users.isPresent()){
//            return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. " +
//                    "Usuário não encontrado pelo id informado");
//        } else {
//            servicesFilter.setUser((User) users.get());
//        }
//        List<Service> services = registerServices.search(servicesFilter);
//        return ResponseEntity.ok(services);
//    }
}
