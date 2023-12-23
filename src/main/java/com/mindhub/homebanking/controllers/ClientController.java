package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.Clientdto;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
     private ClientRepository clientRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;
@RequestMapping("/all")
public List<Clientdto>getAllClient() {
    return clientRepository.findAll().stream().map(client -> new Clientdto(client)).collect(Collectors.toList());
}
@RequestMapping("/{id}")
public  Clientdto getOneClient(@PathVariable Long id){
return  new Clientdto(clientRepository.findById(id).orElse(null));
}

@RequestMapping("/clients/current")
public ResponseEntity<Object> getClient(Authentication authentication){
    Client client= clientRepository.findByEmail(authentication.getName());
    if(client != null) {
        Clientdto clientdto = new Clientdto(client);

        return new ResponseEntity<>(clientdto, HttpStatus.OK);
    }else{
        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }
} ;

    @PostMapping("/clients")
    public ResponseEntity<String>createPerson(
          @RequestParam  String firstName,
          @RequestParam  String lastName,
          @RequestParam  String email,
          @RequestParam  String password){

    if(firstName.isBlank()){
        return  new ResponseEntity<>("The name cannot be blank",HttpStatus.FORBIDDEN);
        }
        if(lastName.isBlank()){
            return  new ResponseEntity<>("The lastname cannot be blank",HttpStatus.FORBIDDEN);
        }if(email.isBlank()){
            return  new ResponseEntity<>("The email cannot be blank",HttpStatus.FORBIDDEN);
        }if(password.isBlank()){
            return  new ResponseEntity<>("The password cannot be blank",HttpStatus.FORBIDDEN);
        }

        if (clientRepository.existsByEmail(email)){
            return new ResponseEntity<>("The email is already in use", HttpStatus.FORBIDDEN);
        }
    Client client = new Client(firstName,lastName,email,passwordEncoder.encode(password));

clientRepository.save(client);
return  new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);

}








}
