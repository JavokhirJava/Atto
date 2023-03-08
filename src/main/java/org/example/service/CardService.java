package org.example.service;

import org.example.container.ComponentContainer;
import org.example.repository.CardRepository;
import org.example.status.CardStatus;

public class CardService {
    private CardRepository cardRepository;
    public void createCard(String number, String exp_date) {
        cardRepository.createCard(number, exp_date);
    }

    public void cardList() {
        cardRepository.getCardList();
    }

    public void updateCard(String number, String exp_date) {
        if (cardRepository.getCard(number) == null) {
            System.out.println("This not card !");
        } else {
            cardRepository.updateCard(number, exp_date);
        }
    }

    public void chStatusCByCardNumber(String cardNumber) {
        if (cardRepository.getCard(cardNumber) == null) {
            System.out.println("Bu karta mavjud emas !");
        } else if (cardRepository.getCard(cardNumber).getStatus().equals(CardStatus.ACTIVE)) {
            cardRepository.changeStatusCardByCardNumber(cardNumber, CardStatus.BLOCK.toString());
        } else if (cardRepository.getCard(cardNumber).getStatus().equals(CardStatus.BLOCK)) {
            cardRepository.changeStatusCardByCardNumber(cardNumber, CardStatus.ACTIVE.toString());
        }
    }
    public void deleteCard(String cardNumber) {
        cardRepository.deleteCard(cardNumber);
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
