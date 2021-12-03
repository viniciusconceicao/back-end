package br.com.searchdevelopers.godev.iugu.dto.response.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IuguTokenExtraInfoResponse {

    private String bin;

    private Integer year;

    private Integer month;

    private String brand;

    @JsonProperty("holder_name")
    private String holderName;

    @JsonProperty("display_number")
    private String displayNumber;

    public IuguTokenExtraInfoResponse() {
    }

    public IuguTokenExtraInfoResponse(String bin, Integer year, Integer month, String brand, String holderName, String displayNumber) {
        this.bin = bin;
        this.year = year;
        this.month = month;
        this.brand = brand;
        this.holderName = holderName;
        this.displayNumber = displayNumber;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(String displayNumber) {
        this.displayNumber = displayNumber;
    }
}
