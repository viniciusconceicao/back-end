package br.com.searchdevelopers.godev.controller;

import br.com.searchdevelopers.godev.domain.Services;
import br.com.searchdevelopers.godev.repository.ServicesRepository;
import br.com.searchdevelopers.godev.usecases.RegisterServices;
import br.com.searchdevelopers.godev.usecases.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    @Autowired
    private ServicesRepository servicesRepository;

    private final RegisterServices registerServices;
    private final RegisterUser registerUser;

    public ServicesController(RegisterServices registerServices, RegisterUser registerUser) {
        this.registerServices = registerServices;
        this.registerUser = registerUser;
    }

//    @PostMapping("/{id}")
//    public ResponseEntity postServices(@Valid @RequestBody Services services,
//                                    @PathVariable Integer id) {
//        try {
//            Optional<Users> users = registerUser.findByIdUser(id);
//            services.setUsers(users.get());
//            registerServices.save(services);
//
//            return ResponseEntity.ok(services);
//        } catch (BusinessRuleException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @GetMapping("/search")
//    public ResponseEntity search(
//            @RequestParam(value = "descriptionService", required = false) String descriptionService,
//            @RequestParam(value = "tag", required = false) String tag,
//            @RequestParam(value = "specially", required = false) String specially,
//            @RequestParam(value = "users") Integer idUser) {
//
//        Services servicesFilter = new Services();
//        servicesFilter.setDescriptionService(descriptionService);
//        servicesFilter.setTag(tag);
//        servicesFilter.setSpecially(specially);
//
//        Optional<Users> users = registerUser.findByIdUser(idUser);
//        if(!users.isPresent()){
//            return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. " +
//                    "Usuário não encontrado pelo id informado");
//        } else {
//            servicesFilter.setUsers(users.get());
//        }
//        List<Services> services = registerServices.search(servicesFilter);
//        return ResponseEntity.ok(services);
//    }

    @GetMapping
    public ResponseEntity getAllServices(){
        if (servicesRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(servicesRepository.findAll());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findByIdServices(@PathVariable Integer id) {
        if (servicesRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(servicesRepository.findById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putServices(@Valid @PathVariable Integer id, @RequestBody Services services) {
        if (servicesRepository.existsById(id)) {
            services.setIdService(id);
            servicesRepository.save(services);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id) {
        if (servicesRepository.existsById(id)) {
            servicesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
