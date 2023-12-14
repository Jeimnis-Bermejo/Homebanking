package com.mindhub.homebanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
@OneToMany(mappedBy = "client" , fetch= FetchType.EAGER)


    private Set<Account> Account=new HashSet<>();

@OneToMany(mappedBy = "client" , fetch= FetchType.EAGER)

    private Set<ClientLoan>ClientLoan=new HashSet<>();
    public Client (){

}
    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Account> getAccount() {

    return Account;
    }

    public void addAcount(Account Account){
    Account.setClient(this);


    this.Account.add(Account);
    }

    public void setAccount(Set<Account> account) {
        Account = account;
    }

    public Set<ClientLoan> getClientLoan() {
        return ClientLoan;
    }

    public void setClientLoan(Set<ClientLoan> clientLoan) {
        ClientLoan = clientLoan;
    }

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setClient(this);
      this.ClientLoan.add(clientLoan) ;

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}//Aca termina la clase


