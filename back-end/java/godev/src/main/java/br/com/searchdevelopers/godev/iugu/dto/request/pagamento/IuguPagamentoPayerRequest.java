package br.com.searchdevelopers.godev.iugu.dto.request.pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguPagamentoPayerRequest {

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    private String name;

    @JsonProperty("phone_prefix")
    private String phonePrefix;

    private String phone;

    private String email;

    public IuguPagamentoPayerRequest() {
    }

    public IuguPagamentoPayerRequest(String cpfCnpj, String name, String phonePrefix, String phone, String email) {
        this.cpfCnpj = cpfCnpj;
        this.name = name;
        this.phonePrefix = phonePrefix;
        this.phone = phone;
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "IuguPagamentoPayerRequest{" +
                "cpfCnpj='" + cpfCnpj + '\'' +
                ", name='" + name + '\'' +
                ", phonePrefix='" + phonePrefix + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
