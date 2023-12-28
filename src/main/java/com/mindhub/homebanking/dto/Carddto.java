package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ColorType;

import java.time.LocalDate;

public class Carddto {
    private Long id;
    private String cardHolder;
    private CardType type;
    private ColorType color;
    private String number;
    private Integer cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;


    public Carddto(Card card){

        id=card.getId();
        cardHolder=card.getCardHolder();
        type=card.getType();
        color=card.getColor();
        number=card.getNumber();
        cvv=card.getCvv();
        thruDate=card.getThruDate();
        fromDate=card.getFromDate();
    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public ColorType getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }
}
