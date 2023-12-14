package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ClienLoandto {


    private Long id ;
    private Double amount;
    private Integer payments;
    private Long  loanid;
      private String name;

    public ClienLoandto(ClientLoan clientLoan){

        id=clientLoan.getId();
        loanid=clientLoan.getLoan().getId();
        name=clientLoan.getLoan().getName();
        amount=clientLoan.getAmount();
        payments=clientLoan.getPayments();

    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public Long getLoanid() {
        return loanid;
    }


    public String getName() {
        return name;
    }
}
