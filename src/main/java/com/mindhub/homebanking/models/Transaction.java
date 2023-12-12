package com.mindhub.homebanking.models;

import jakarta.persistence.*;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import java.net.Proxy;
import java.time.LocalDate;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type;

    private Double amount;

    private String descripcion;

    private LocalDate date;



    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Transaction(TransactionType type, Double amount, String descripcion, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.descripcion = descripcion;
        this.date = date;
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


    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}


