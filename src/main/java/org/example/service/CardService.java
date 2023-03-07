package org.example.service;

import org.example.container.ComponentContainer;
import org.example.status.CardStatus;

public class CardService {
    public void createCard(String number, String exp_date) {
        ComponentContainer.cRepository.createCard(number, exp_date);
    }

    public void cardList() {
        ComponentContainer.cRepository.getCardList();
    }

    public void updateCard(String number, String exp_date) {
        if (ComponentContainer.cRepository.getCard(number) == null) {
            System.out.println("This not card !");
        } else {
            ComponentContainer.cRepository.updateCard(number, exp_date);
        }
    }

    public void chStatusCByCardNumber(String cardNumber) {
        if (ComponentContainer.cRepository.getCard(cardNumber) == null) {
            System.out.println("Bu karta mavjud emas !");
        } else if (ComponentContainer.cRepository.getCard(cardNumber).getStatus().equals(CardStatus.ACTIVE)) {
            ComponentContainer.cRepository.changeStatusCardByCardNumber(cardNumber, CardStatus.BLOCK.toString());
        } else if (ComponentContainer.cRepository.getCard(cardNumber).getStatus().equals(CardStatus.BLOCK)) {
            ComponentContainer.cRepository.changeStatusCardByCardNumber(cardNumber, CardStatus.ACTIVE.toString());
        }
    }
    public void deleteCard(String cardNumber) {
        ComponentContainer.cRepository.deleteCard(cardNumber);
    }
}
