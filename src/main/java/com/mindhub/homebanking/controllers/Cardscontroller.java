package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class Cardscontroller {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<String> CreateCards(@RequestParam CardType type,
                                              @RequestParam ColorType color,
                                              Authentication authentication) {

        Client client = clientRepository.findByEmail(authentication.getName());
        long colorType = client.getCard().stream()
                .filter(card -> card.getColor() == color && card.getType() == type)
                .count();
        long colors = client.getCard().stream().filter(card -> card.getColor() == color).count();
        long types = client.getCard().stream().filter(card -> card.getType() == type).count();

        if(types >= 3){
            return new ResponseEntity<>("It cannot have more than 3 cards."+ type, HttpStatus.FORBIDDEN);
        }
        if (colors >= 2){
            return new ResponseEntity<>("It cannot have more cards of this color."+ color,HttpStatus.FORBIDDEN);
        }
        if (colorType >= 1){
            return new ResponseEntity<>("It cannot have more cards of this color"+ color + "and this type"+ type, HttpStatus.FORBIDDEN);
        }
        if (client.getCard().size()>=6){
            return new ResponseEntity<>(
                    "Exceded the number of cards it can have; its limit is 6.", HttpStatus.FORBIDDEN);
        }


        int cvv = (int)(Math.random()* 999 + 100);
        String number = getCardNumber();
        String cardHolder = client.getFirstName() + " " + client.getLastName();
        LocalDate fromDate = LocalDate.now();
        LocalDate thruDate = LocalDate.now().plusYears(5);

        Card card = new Card(cardHolder, type,color,number,cvv,thruDate,fromDate);
        client.addCard(card);
        cardRepository.save(card);
        return new ResponseEntity<>("card created", HttpStatus.CREATED);

    }
    private String getCardNumber(){
        StringBuilder cardNumber = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int section = (int)(Math.random() * 9000 + 1000);
            cardNumber.append(section).append("-");
        }
        return cardNumber.substring(0,cardNumber.length()-1);

        }


    }





