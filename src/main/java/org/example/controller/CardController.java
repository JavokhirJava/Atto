package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.CardService;
import org.springframework.stereotype.Component;

@Component
public class CardController {
    private CardService cardService;
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> addCard();
                case 2 -> cardList();
                case 3 -> updateCard();
                case 4 -> cardChangeStatus();
                case 5 -> deleteCard();
                default -> b=false;
            }
        }
    }

    public Integer menu() {
        System.out.println("1.Create Card ");
        System.out.println("2.Card List ");
        System.out.println("3.Update Card ");
        System.out.println("4.Change Card Status ");
        System.out.println("5.Delete Card ");
        System.out.println("Enter action ");
        Integer n = ComponentContainer.scanInteger.nextInt();
        return n;
    }

    private void addCard() {
        System.out.println("Enter number ");
        String number = ComponentContainer.scanString.nextLine();
        System.out.println("Enter exp_date ");
        String exp_date = ComponentContainer.scanString.nextLine();
        cardService.createCard(number, exp_date);
    }

    private void cardList() {
        cardService.cardList();
    }

    private void cardChangeStatus() {
        System.out.println("Enter Card Number :");
        cardService.chStatusCByCardNumber(ComponentContainer.scanString.nextLine());
    }

    private void deleteCard() {
        System.out.println("Enter Card Number :");
        cardService.deleteCard(ComponentContainer.scanString.nextLine());
    }

    private void updateCard() {
        System.out.println("Enter Number :");
        String number = ComponentContainer.scanString.nextLine();
        System.out.println("Enter exp_date :");
        String exp_date = ComponentContainer.scanString.nextLine();
        cardService.updateCard(number, exp_date);
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }
}
