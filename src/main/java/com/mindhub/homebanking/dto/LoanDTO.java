package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoanDTO {
    private final Long id;
    private final String name;
    private final Double maxAmount;
    private List<Integer> payments =new ArrayList<>();

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }
}
