package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Transaction;
import org.example.status.CardStatus;
import org.example.status.TransactionType;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;



public class UserService {
    public void addCard(String cardNumber, Profile profile) {
        if (ComponentContainer.cRepository.getCard(cardNumber) == null) {
            System.err.println("Carta Bazadan topilmadi !");
        } else {
            ComponentContainer.cRepository.updateCardToPhone(cardNumber, profile.getPhone());
        }
    }

    public void cardList(Profile profile) {
        if (ComponentContainer.cRepository.cardListByPhone(profile) == null) {
            System.out.println("Sizda bitta ham karta yo'q !");
        } else {
            ComponentContainer.cRepository.cardListByPhone(profile).forEach(card -> {
                System.out.println("CardNumber : " + card.getNumber() + " exp_date : " + card.getExp_date() + " balance : " + card.getBalance());
            });
        }//number,exp_date,balance
    }

    public void changeCardStatus(String cardNumber, Profile profile) {
        if (!ComponentContainer.cRepository.getCard(cardNumber).getPhone().equals(profile.getPhone()) || ComponentContainer.cRepository.getCard(cardNumber) == null) {
            System.err.println("Nimadur xato ketdi !!! ");
        } else if (ComponentContainer.cRepository.getStatus(profile.getPhone()).equals(CardStatus.ACTIVE.toString())) {
            ComponentContainer.cRepository.cChStatus(CardStatus.BLOCK.toString(), profile.getPhone());
        } else if (ComponentContainer.cRepository.getStatus(profile.getPhone()).equals(CardStatus.BLOCK.toString())) {
            ComponentContainer.cRepository.cChStatus(CardStatus.ACTIVE.toString(), profile.getPhone());
        }
    }

    public void deleteCard(String cardNumber, Profile profile) {
        if (ComponentContainer.cRepository.getCard(cardNumber).getPhone().equals(profile.getPhone()))
            ComponentContainer.cRepository.changePhone(cardNumber, null);
    }

    public void refill(String number, String code, Double balance) {
        if (ComponentContainer.cRepository.getCard(number).getPhone().equals(ComponentContainer.profile.getPhone()) && ComponentContainer.terminalRepository.getTerminal(code) != null) {
            ComponentContainer.cRepository.refill(number, code, balance, ComponentContainer.cRepository.getCard(number));
        } else {
            System.err.println("Nimadur xato ketdi !!! ");
        }
    }

    public void transaction(List<Card> cardListByPhone) {
        List<Transaction> transactionList = new LinkedList<>();
        for (Transaction transaction : ComponentContainer.transactionRepository.transactionList()) {
            for (Card card : cardListByPhone) {
                if (transaction.getCard_number().equals(card.getNumber())) {
                    transactionList.add(transaction);
                }
            }
        }
        transactionList.stream().forEach(System.out::println);
    }

    public void makePayment(String number, String code, Profile profile) {
        Transaction transaction = null;
        if (ComponentContainer.cRepository.getCard(number) == null || ComponentContainer.terminalRepository.getTerminal(code) == null || !ComponentContainer.cRepository.getCard(number).getPhone().equals(profile.getPhone()) ) {
            System.out.println("Error ! ! ! ");
        } else {
            transaction=new Transaction(number,ComponentContainer.TOLL,code,TransactionType.PAYMENT, LocalDateTime.now());
            ComponentContainer.cRepository.makePayment(number,code,ComponentContainer.TOLL,ComponentContainer.cRepository.getCard(number));
        }
    }
}
//card_number,amount,terminal_code,type,created_date
