package br.com.searchdevelopers.godev.iugu.dto.response.pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguPagamentoResponse {

    private String message;

    private String status;

    @JsonProperty("info_message")
    private String infoMessage;

    private String reversible;

    private String token;

    private String brand;

    private String bin;

    private Boolean success;

    private String url;

    private String pdf;

    private String identification;

    @JsonProperty("invoice_id")
    private String invoiceId;

    @JsonProperty("LR")
    private String lr;

    public IuguPagamentoResponse() {
    }

    public IuguPagamentoResponse(String message, String status, String infoMessage, String reversible, String token, String brand, String bin, Boolean success, String url, String pdf, String identification, String invoiceId, String lr) {
        this.message = message;
        this.status = status;
        this.infoMessage = infoMessage;
        this.reversible = reversible;
        this.token = token;
        this.brand = brand;
        this.bin = bin;
        this.success = success;
        this.url = url;
        this.pdf = pdf;
        this.identification = identification;
        this.invoiceId = invoiceId;
        this.lr = lr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getReversible() {
        return reversible;
    }

    public void setReversible(String reversible) {
        this.reversible = reversible;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }
}
