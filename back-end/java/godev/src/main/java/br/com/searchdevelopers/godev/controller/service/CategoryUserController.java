package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.CategoryUser;
import br.com.searchdevelopers.godev.exceptions.AuthenticationErrorException;
import br.com.searchdevelopers.godev.repository.CategoryUserRepository;
import br.com.searchdevelopers.godev.usecases.CategoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryUserController {

    @Autowired
    private CategoryUserRepository categoryUserRepository;
    private final CategoryUserService categoryUserService;

    public CategoryUserController(CategoryUserRepository categoryUserRepository,
                                  CategoryUserService categoryUserService) {
        this.categoryUserRepository = categoryUserRepository;
        this.categoryUserService = categoryUserService;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity saveTypeCategory(@Valid @PathVariable Integer idUser, @Valid @RequestBody CategoryUser categoryUser) {
        try {
            this.categoryUserService.saveCategoryUser(idUser, categoryUser);
            return ResponseEntity.created(null).build();
        } catch (AuthenticationErrorException var4) {
            return ResponseEntity.status(401).body("Usuário com id errado.");
        }
    }

    @GetMapping("/filter/{idUser}")
    public ResponseEntity getCategory(@PathVariable Integer idUser) {
        return this.categoryUserService.getUserIdCategory(idUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity findByIdCategory(@PathVariable Integer id) {
        return this.categoryUserRepository.findById(id).isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(this.categoryUserRepository.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAllCategories() {
        this.categoryUserRepository.findAll();
        return ResponseEntity.ok(this.categoryUserRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity putCategory(@PathVariable Integer id,
                                      @RequestBody CategoryUser categoryUser) {
        if (categoryUserRepository.existsById(id)) {
            categoryUser.setIdCategory(id);
            categoryUserRepository.save(categoryUser);
            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (categoryUserRepository.existsById(id)) {
            categoryUserRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
