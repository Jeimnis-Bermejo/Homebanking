package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.Accountdto;
import com.mindhub.homebanking.dto.Clientdto;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/all")
    public List<Accountdto> getAllAccount() {
        return accountRepository.findAll().stream().map(account-> new Accountdto(account)).collect(Collectors.toList());
    }
    @RequestMapping("/{id}")
    public  Accountdto getOneAccount(@PathVariable Long id){
        return  new Accountdto(accountRepository.findById(id).orElse(null));

    }

}

