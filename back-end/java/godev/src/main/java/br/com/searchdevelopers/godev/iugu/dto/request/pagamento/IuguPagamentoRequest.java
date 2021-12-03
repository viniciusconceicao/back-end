package br.com.searchdevelopers.godev.iugu.dto.request.pagamento;

import java.util.ArrayList;
import java.util.List;

public class IuguPagamentoRequest {

    private List<IuguPagamentoItemRequest> items = new ArrayList<>();

    private IuguPagamentoPayerRequest payer;

    private String token;

    private String email;

    public IuguPagamentoRequest() {
    }

    public IuguPagamentoRequest(List<IuguPagamentoItemRequest> items, IuguPagamentoPayerRequest payer, String token, String email) {
        this.items = items;
        this.payer = payer;
        this.token = token;
        this.email = email;
    }

    public List<IuguPagamentoItemRequest> getItems() {
        return items;
    }

    public void setItems(List<IuguPagamentoItemRequest> items) {
        this.items = items;
    }

    public IuguPagamentoPayerRequest getPayer() {
        return payer;
    }

    public void setPayer(IuguPagamentoPayerRequest payer) {
        this.payer = payer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "IuguPagamentoRequest{" +
                "items=" + items +
                ", payer=" + payer +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
