package br.com.searchdevelopers.godev.dto;

public class CreditCardDto {
    private String numberCard;

    private String cvv;

    private String monthCard;

    private String yearCard;

    public CreditCardDto() {
    }

    public CreditCardDto(String numberCard, String cvv, String monthCard, String yearCard) {
        this.numberCard = numberCard;
        this.cvv = cvv;
        this.monthCard = monthCard;
        this.yearCard = yearCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getMonthCard() {
        return monthCard;
    }

    public void setMonthCard(String monthCard) {
        this.monthCard = monthCard;
    }

    public String getYearCard() {
        return yearCard;
    }

    public void setYearCard(String yearCard) {
        this.yearCard = yearCard;
    }
}
