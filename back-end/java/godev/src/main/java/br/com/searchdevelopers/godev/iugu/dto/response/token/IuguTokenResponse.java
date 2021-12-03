package br.com.searchdevelopers.godev.iugu.dto.response.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguTokenResponse {

    private String id;

    private String method;

    @JsonProperty("extra_info")
    private IuguTokenExtraInfoResponse extraInfo;

    private Boolean test;

    public IuguTokenResponse() {
    }

    public IuguTokenResponse(String id, String method, IuguTokenExtraInfoResponse extraInfo, Boolean test) {
        this.id = id;
        this.method = method;
        this.extraInfo = extraInfo;
        this.test = test;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public IuguTokenExtraInfoResponse getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(IuguTokenExtraInfoResponse extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "IuguTokenResponse{" +
                "id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", extraInfo=" + extraInfo +
                ", test=" + test +
                '}';
    }
}
