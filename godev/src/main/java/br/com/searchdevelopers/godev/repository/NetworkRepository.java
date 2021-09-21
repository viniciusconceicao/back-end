package br.com.searchdevelopers.godev.repository;

import br.com.searchdevelopers.godev.domain.Formation;
import br.com.searchdevelopers.godev.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Integer> {

    //Foi tirado para fazer um teste esse atributo está sendo usado no NetWorkController,
    //para ativalo novamente é só descomentar aqui e copiar o comentario que está na linha
    //58 do network repository
    //Formation findByUsersIdUser(Integer idUser);
}
