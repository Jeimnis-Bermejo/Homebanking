package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.Clientdto;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
     private ClientRepository clientRepository;
@RequestMapping("/all")
public List<Clientdto>getAllClient() {
    return clientRepository.findAll().stream().map(client -> new Clientdto(client)).collect(Collectors.toList());
}
@RequestMapping("/{id}")
public  Clientdto getOneClient(@PathVariable Long id){
return  new Clientdto(clientRepository.findById(id).orElse(null));

}

}
