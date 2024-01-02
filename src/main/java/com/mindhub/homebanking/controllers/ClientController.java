package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dto.Accountdto;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.dto.Clientdto;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping("/all")
    public List<Clientdto> getAllClient() {
        return clientRepository.findAll().stream().map(client -> new Clientdto(client)).collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public Clientdto getOneClient(@PathVariable Long id) {
        return new Clientdto(clientRepository.findById(id).orElse(null));
    }

    @RequestMapping("/clients/current")
    public ResponseEntity<Object> getClient(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        if (client != null) {
            Clientdto clientdto = new Clientdto(client);

            return new ResponseEntity<>(clientdto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clients/current/accounts")
    public List<Accountdto> getAccounts(Authentication authentication) {
        return clientRepository.findByEmail(authentication.getName()).getAccount().stream().map(Accountdto::new).collect(Collectors.toList());
    }

    @PostMapping("/clients")
    public ResponseEntity<String> createPerson(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {

        if (firstName.isBlank()) {
            return new ResponseEntity<>("The name cannot be blank", HttpStatus.FORBIDDEN);
        }
        if (lastName.isBlank()) {
            return new ResponseEntity<>("The lastname cannot be blank", HttpStatus.FORBIDDEN);
        }
        if (email.isBlank()) {
            return new ResponseEntity<>("The email cannot be blank", HttpStatus.FORBIDDEN);
        }
        if (password.isBlank()) {
            return new ResponseEntity<>("The password cannot be blank", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.existsByEmail(email)) {
            return new ResponseEntity<>("The email is already in use", HttpStatus.FORBIDDEN);
        }
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));

        Account account = new Account("VIN-" + String.format("%06d", new Random().nextInt(0, 1000000)),
                0d, LocalDate.now());
        clientRepository.save(client);
        client.addAcount(account);
        accountRepository.save(account);

        clientRepository.save(client);
        return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);


    }


}
