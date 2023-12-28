package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.Accountdto;
import com.mindhub.homebanking.dto.Clientdto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/accounts")
    public List<Accountdto> getAllAccount() {
        return accountRepository.findAll().stream().map(account -> new Accountdto(account)).collect(Collectors.toList());
    }

    @RequestMapping("/accounts/{id}")
    public Accountdto getOneAccount(@PathVariable Long id) {
        return new Accountdto(accountRepository.findById(id).orElse(null));

    }

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping ("/clients/current/accounts")
    public ResponseEntity<String> CreateAccount(Authentication authentication) {

        Client client = clientRepository.findByEmail(authentication.getName());

        if (client.getAccount().size() >= 3) {
            return new ResponseEntity<>("usted ya posse el limite de cuentas", HttpStatus.FORBIDDEN);
        }
        String number=generateNumber();
        Double balance=0d;
        LocalDate createDate=LocalDate.now();
        Account account = new Account(number,balance,createDate);

        client.addAcount(account);
        accountRepository.save(account);

        return new ResponseEntity<>("cuenta creada con exito", HttpStatus.CREATED);
    }


     public String generateNumber (){
         String number;
         do {
             number = "VIN-" + String.format("%06d", new Random().nextInt(0, 1000000));
         } while (accountRepository.existsByNumber(number));
         return number;
     }

}


