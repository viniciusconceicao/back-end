package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool,Integer> {

    List<Tool> findByUserIdUser(Integer id);
}
