package br.com.searchdevelopers.godev.iugu.dto.request.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguTokenRequest {

    private IuguTokenDataRequest data;

    @JsonProperty("account_id")
    private String accountId;

    private String method;

    private Boolean test;

    public IuguTokenRequest() {
    }

    public IuguTokenRequest(IuguTokenDataRequest data) {
        this.data = data;
        this.accountId = "77B7A2239B9E4DA3B5C99E750E2455C8";
        this.method = "credit_card";
        this.test = true;
    }

    public IuguTokenDataRequest getData() {
        return data;
    }

    public void setData(IuguTokenDataRequest data) {
        this.data = data;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }
}
