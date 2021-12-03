package br.com.searchdevelopers.godev.iugu.service;


import br.com.searchdevelopers.godev.iugu.dto.request.pagamento.IuguPagamentoRequest;
import br.com.searchdevelopers.godev.iugu.dto.request.token.IuguTokenRequest;
import br.com.searchdevelopers.godev.iugu.dto.response.pagamento.IuguPagamentoResponse;
import br.com.searchdevelopers.godev.iugu.dto.response.token.IuguTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "criarToken", url = "https://api.iugu.com/v1/")
interface IuguService {

    @PostMapping(path = "/payment_token", consumes = "application/json", produces = "application/json")
    IuguTokenResponse getToken(@RequestBody IuguTokenRequest iuguTokenRequest);

    @PostMapping(path = "/charge?api_token=B988208139D82983BAD7AD67D0733CC409B48E7E47A2399E15796D95CD71B90C",
            consumes = "application/json", produces = "application/json")
    IuguPagamentoResponse realizarPagamento(@RequestBody IuguPagamentoRequest iuguPagamentoRequest);
}
