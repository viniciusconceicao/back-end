package br.com.searchdevelopers.godev.iugu.dto.request.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguTokenDataRequest {

    private String number;

    @JsonProperty("verification_value")
    private String verificationValue;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String month;

    private String year;

    public IuguTokenDataRequest() {
    }

    public IuguTokenDataRequest(String number, String verificationValue, String firstName, String lastName, String month, String year) {
        this.number = number;
        this.verificationValue = verificationValue;
        this.firstName = firstName;
        this.lastName = lastName;
        this.month = month;
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVerificationValue() {
        return verificationValue;
    }

    public void setVerificationValue(String verificationValue) {
        this.verificationValue = verificationValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
