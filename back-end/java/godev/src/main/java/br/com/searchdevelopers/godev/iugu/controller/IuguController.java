package br.com.searchdevelopers.godev.iugu.controller;


import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.iugu.dto.request.pagamento.IuguPagamentoRequest;
import br.com.searchdevelopers.godev.iugu.dto.response.pagamento.IuguPagamentoResponse;
import br.com.searchdevelopers.godev.iugu.service.PagamentoService;
import br.com.searchdevelopers.godev.repository.UserRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class IuguController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private UserRepository userRepository;
    /*
    Rota pata gerarar o pagamento
    */
    @PostMapping("/gerar-pagamento/{idUser}")
    public ResponseEntity<?> gerarPagamento(@PathVariable Integer idUser) {
        Users users = userRepository.findUserByIdUser(idUser);

        try {
            IuguPagamentoResponse pagamento = this.pagamentoService.gerarPagamento( idUser);
            users.setPremium(true);
            userRepository.save(users);
            return ResponseEntity.ok(pagamento);
        } catch (FeignException e) {
            users.setPremium(false);
            userRepository.save(users);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
