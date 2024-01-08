package com.mindhub.homebanking.dto;

public class LoanApplicationDTO {

    Long id;
    Double amount;
    Integer payments;
    String accountNumber;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
