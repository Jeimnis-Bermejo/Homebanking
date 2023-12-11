package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Transactiondto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type;

    private Double amount;

    private String descripcion;

    private LocalDate date;

    public Transactiondto(Transaction transaction) {

        id=transaction.getId();
        type=transaction.getType();
        amount=transaction.getAmount();
        descripcion=transaction.getDescripcion();
        date=transaction.getDate();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getDate() {
        return date;
    }
}
