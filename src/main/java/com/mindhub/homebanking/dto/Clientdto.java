package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Clientdto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<Accountdto> accounts;

    private List<ClienLoandto> loans;

private List<Carddto>cards;

    public Clientdto(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        accounts = client.getAccount().stream().map(account -> new Accountdto(account)).collect(Collectors.toList());
        loans = client.getClientLoan().stream().map(clientLoan -> new ClienLoandto(clientLoan)).collect(Collectors.toList());
        cards=client.getCard().stream().map(card -> new Carddto(card)).collect(Collectors.toList());
    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    public List<Accountdto> getAccounts() {
        return accounts;
    }

    public List<ClienLoandto> getLoans() {
        return loans;
    }

    public List<Carddto> getCards() {
        return cards;
    }
}
