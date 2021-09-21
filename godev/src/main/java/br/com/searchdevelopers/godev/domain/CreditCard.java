package br.com.searchdevelopers.godev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class CreditCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCreditCard;

  private String numberCard;

  private String cvv;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM")
  private LocalDate monthCard;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
  private LocalDate yearCard;

  @ManyToOne
  @JoinColumn(name = "users_id")
  private Users users;

  public Integer getIdCreditCard() {
    return idCreditCard;
  }

  public void setIdCreditCard(Integer idCreditCard) {
    this.idCreditCard = idCreditCard;
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

  public LocalDate getMonthCard() {
    return monthCard;
  }

  public void setMonthCard(LocalDate monthCard) {
    this.monthCard = monthCard;
  }

  public LocalDate getYearCard() {
    return yearCard;
  }

  public void setYearCard(LocalDate yearCard) {
    this.yearCard = yearCard;
  }

  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }
}
