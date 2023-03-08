package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Transaction;
import org.example.repository.CardRepository;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.example.status.CardStatus;
import org.example.status.TransactionType;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;



public class UserService {
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;
    private TerminalRepository terminalRepository;
    public void addCard(String cardNumber, Profile profile) {
        if (cardRepository.getCard(cardNumber) == null) {
            System.err.println("Carta Bazadan topilmadi !");
        } else {
            cardRepository.updateCardToPhone(cardNumber, profile.getPhone());
        }
    }

    public void cardList(Profile profile) {
        if (cardRepository.cardListByPhone(profile) == null) {
            System.out.println("Sizda bitta ham karta yo'q !");
        } else {
            cardRepository.cardListByPhone(profile).forEach(card -> {
                System.out.println("CardNumber : " + card.getNumber() + " exp_date : " + card.getExp_date() + " balance : " + card.getBalance());
            });
        }//number,exp_date,balance
    }

    public void changeCardStatus(String cardNumber, Profile profile) {
        if (!cardRepository.getCard(cardNumber).getPhone().equals(profile.getPhone()) || cardRepository.getCard(cardNumber) == null) {
            System.err.println("Nimadur xato ketdi !!! ");
        } else if (cardRepository.getStatus(profile.getPhone()).equals(CardStatus.ACTIVE.toString())) {
            cardRepository.cChStatus(CardStatus.BLOCK.toString(), profile.getPhone());
        } else if (cardRepository.getStatus(profile.getPhone()).equals(CardStatus.BLOCK.toString())) {
            cardRepository.cChStatus(CardStatus.ACTIVE.toString(), profile.getPhone());
        }
    }

    public void deleteCard(String cardNumber, Profile profile) {
        if (cardRepository.getCard(cardNumber).getPhone().equals(profile.getPhone()))
            cardRepository.changePhone(cardNumber, null);
    }

    public void refill(String number, String code, Double balance) {
        if (cardRepository.getCard(number).getPhone().equals(ComponentContainer.profile.getPhone()) && terminalRepository.getTerminal(code) != null) {
            cardRepository.refill(number, code, balance, cardRepository.getCard(number));
        } else {
            System.err.println("Nimadur xato ketdi !!! ");
        }
    }

    public void transaction(List<Card> cardListByPhone) {
        List<Transaction> transactionList = new LinkedList<>();
        for (Transaction transaction : transactionRepository.transactionList()) {
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
        if (cardRepository.getCard(number) == null || terminalRepository.getTerminal(code) == null || !cardRepository.getCard(number).getPhone().equals(profile.getPhone()) ) {
            System.out.println("Error ! ! ! ");
        } else {
            transaction=new Transaction(number,ComponentContainer.TOLL,code,TransactionType.PAYMENT, LocalDateTime.now());
            cardRepository.makePayment(number,code,ComponentContainer.TOLL,cardRepository.getCard(number));
        }
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setTerminalRepository(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }
}

//card_number,amount,terminal_code,type,created_date
