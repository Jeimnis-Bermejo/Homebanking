package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;

public class Accountdto {

    private Long id;
    private String number;

    private Double balance;

    private LocalDate creationDate;

    public Accountdto(Account account){

        id= account.getId();
        number= account.getNumber();
        balance= account.getBalance();
        creationDate=account.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
