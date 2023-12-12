package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class Clientdto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<Accountdto>accounts;



    public Clientdto(Client client){
 id= client.getId();
 firstName= client.getFirstName();
 lastName= client.getLastName();
 email= client.getEmail();
 accounts=client.getAccount().stream().map(account -> new Accountdto(account)).collect(Collectors.toList());

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
}
