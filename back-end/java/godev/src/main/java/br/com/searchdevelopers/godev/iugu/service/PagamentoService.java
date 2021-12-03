package br.com.searchdevelopers.godev.iugu.service;


import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.iugu.dto.request.pagamento.IuguPagamentoItemRequest;
import br.com.searchdevelopers.godev.iugu.dto.request.pagamento.IuguPagamentoPayerRequest;
import br.com.searchdevelopers.godev.iugu.dto.request.pagamento.IuguPagamentoRequest;
import br.com.searchdevelopers.godev.iugu.dto.request.token.IuguTokenDataRequest;
import br.com.searchdevelopers.godev.iugu.dto.request.token.IuguTokenRequest;
import br.com.searchdevelopers.godev.iugu.dto.response.pagamento.IuguPagamentoResponse;
import br.com.searchdevelopers.godev.iugu.dto.response.token.IuguTokenResponse;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PagamentoService {


    @Autowired
    private IuguService iuguService;

    @Autowired
    private UserRepository userRepository;




    public IuguPagamentoResponse gerarPagamento(Integer idUser) {

        Users users = userRepository.findUserByIdUser(idUser);

        IuguPagamentoRequest iuguPagamentoRequest = new IuguPagamentoRequest();


        String[] nomeCompleto = users.getNameUser().split(" ");
        String number = users.getNumberCard();
        String verificationValue = users.getCvv();
        String firstName = nomeCompleto.length > 0 ? nomeCompleto[0] : "";
        String lastName = nomeCompleto.length > 1 ? nomeCompleto[1] : "";
        String month = users.getMonthCard();
        String year = users.getYearCard();


        IuguTokenRequest iuguTokenRequest = new IuguTokenRequest(
                new IuguTokenDataRequest(
                        number,
                        verificationValue,
                        firstName,
                        lastName,
                        month,
                        year
                )
        );

        IuguTokenResponse token = this.iuguService.getToken(iuguTokenRequest);

        IuguPagamentoPayerRequest payer = new IuguPagamentoPayerRequest(users.getCpf() == null ? users.getCnpj(): users.getCpf(),
                users.getNameUser(),
                "11",
                users.getPhone(),
                users.getEmail());

        List<IuguPagamentoItemRequest> items = new ArrayList<>();
        IuguPagamentoItemRequest item = new  IuguPagamentoItemRequest("Premiun Go",1,8990);

        items.add(item);

        iuguPagamentoRequest.setItems(items);
        iuguPagamentoRequest.setToken(token.getId());
        iuguPagamentoRequest.setPayer(payer);
        iuguPagamentoRequest.setEmail(users.getEmail());


        return this.iuguService.realizarPagamento(iuguPagamentoRequest);
    }



}
