package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    List<Language> findByUserIdUser(Integer id);
}
