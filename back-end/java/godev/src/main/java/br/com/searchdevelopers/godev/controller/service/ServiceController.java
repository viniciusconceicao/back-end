package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Service;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.exceptions.BusinessRuleException;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import br.com.searchdevelopers.godev.usecases.AsyncFilaService;
import br.com.searchdevelopers.godev.usecases.RegisterService;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@EnableAsync
@RestController
@RequestMapping("/api/services")
public class ServiceController {




    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Autowired
    private AsyncFilaService asyncFilaService;

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
            Optional<Users> userClt = registerUser.findByIdUser(idClt);
            Optional<Users> userDev = registerUser.findByIdUser(idDev);

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


    @Async
    @Scheduled(cron = "* * 4 * * *")
    public void asyncFila(){

        if (!userServiceRepository.findAll().isEmpty()){
           asyncFilaService.expirarPublicacao();
        }

    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity getInactiveTypeUser(@PathVariable int id){

        Integer progressInitiated = 0;
        List<UserService> devs = userServiceRepository
                .findByUsersDevIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressInitiated);
        List<UserService> clients = userServiceRepository
                .findByUsersCltIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressInitiated);

        boolean isDev = userServiceRepository.existsByUsersDevIdUserAndUsersDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUsersCltIdUserAndUsersCltRoleEquals(id, "clt");



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
        Integer progressInitiated = 0;
        List<UserService> inactiveServices = userServiceRepository
                .findByServiceActiveServiceFalseAndServiceProgress(progressInitiated);
        if (inactiveServices.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(inactiveServices);
        }
    }

    @GetMapping("/active/{id}")
    public ResponseEntity getActiveTypeUser(@PathVariable int id){

        List<UserService> devs = userServiceRepository.findByUsersDevIdUserAndServiceActiveServiceTrue(id);
        List<UserService> clients = userServiceRepository.findByUsersCltIdUserAndServiceActiveServiceTrue(id);

        boolean isDev = userServiceRepository.existsByUsersDevIdUserAndUsersDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUsersCltIdUserAndUsersCltRoleEquals(id, "clt");

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
    public ResponseEntity getFinishedTypeUser(@PathVariable int id){

        Integer progressFinished = 100;
        List<UserService> devs = userServiceRepository
                .findByUsersDevIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressFinished);
        List<UserService> clients = userServiceRepository
                .findByUsersCltIdUserAndServiceActiveServiceFalseAndServiceProgress(id, progressFinished);

        boolean isDev = userServiceRepository.existsByUsersDevIdUserAndUsersDevRoleEquals(id, "dev");
        boolean isClt = userServiceRepository.existsByUsersCltIdUserAndUsersCltRoleEquals(id, "clt");

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
        Integer progressFinished = 100;
        List<UserService> finishedServices = userServiceRepository
                .findByServiceActiveServiceFalseAndServiceProgress(progressFinished);
        if (finishedServices.isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(finishedServices);
        }
    }

    @GetMapping
    public ResponseEntity getAllServices(){
        if (serviceRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(serviceRepository.findAll());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdService(@PathVariable Integer id) {
        if (serviceRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(serviceRepository.findById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/{idService}/user/{idUser}")
    public ResponseEntity findByIdServiceAndIdUser(@PathVariable Integer idService,
                                                   @PathVariable Integer idUser) {

        UserService dev = userServiceRepository.findByServiceIdServiceAndUsersCltIdUser(idService, idUser);
        UserService client = userServiceRepository.findByServiceIdServiceAndUsersDevIdUser(idService, idUser);

        boolean isDev = userServiceRepository.existsByUsersDevIdUserAndUsersDevRoleEquals(idUser, "dev");
        boolean isClt = userServiceRepository.existsByUsersCltIdUserAndUsersCltRoleEquals(idUser, "clt");

        if (isDev){
            return ResponseEntity.ok(client);
        } else if (isClt){
            return ResponseEntity.ok(dev);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putServices(@Valid @PathVariable Integer id, @RequestBody Service service) {
        if (serviceRepository.existsById(id)) {
            service.setIdService(id);
            if(service.getProgress() >= 100){
                service.setActiveService(false);
            }
            serviceRepository.save(service);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id) {
        if (userServiceRepository.existsById(id)) {
            userServiceRepository.deleteById(id);
            serviceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity search(
//            @RequestParam(value = "descriptionService", required = false) String descriptionService,
//            @RequestParam(value = "tag", required = false) String tag,
//            @RequestParam(value = "user", required = false) Integer idUser) {
//
//        Service servicesFilter = new Service();
//        servicesFilter.setDescriptionService(descriptionService);
//        servicesFilter.setTag(tag);
//
//        Optional<Users> user = registerUser.findByIdUser(idUser);
//        if(!user.isPresent()){
//            return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. " +
//                    "Usuário não encontrado pelo id informado");
//        } else {
//            servicesFilter.setUser((Users) user.get());
//        }
//        List<Service> services = registerServices.search(servicesFilter);
//        return ResponseEntity.ok(services);
//    }
}
