package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Transaction;
import org.example.repository.CardRepository;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TransactionController {
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private CardRepository cardRepository;
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> transactionList();
                case 2 -> companyCardBalance();
                case 3 -> todayPayments();      //////Bugungi to'lovlar
                case 4 -> dailyPayments();
                case 5 -> oraliqTolovlar();
                case 6 -> generalBalance();
                case 7 -> transactionByTerminal();
                case 8 -> transactionByCard();
                default -> b = false;
            }
        }
    }

    public Integer menu() {
        System.out.println("Transaction Menu ");
        System.out.println("1.Transaction List ");
        System.out.println("2.Company Card Balance ");
        System.out.println("3.Bugungi to'lovlar ");
        System.out.println("4.Kunlik to'lovlar ");
        System.out.println("5.Oraliq to'lovlar ");
        System.out.println("6.Umumiy balance ");
        System.out.println("7.Transaction by Terminal ");
        System.out.println("8.Transaction By Card ");
        System.out.println("Enter action :");
        return ComponentContainer.scanInteger.nextInt();
    }

    private void transactionList() {
        transactionService.transactionList();
    }

    private void companyCardBalance() {
        cardRepository.companyCardBalance();
    }

    private void todayPayments() {
        LinkedList<Transaction> transactions = transactionRepository.todayPayments();
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    private void dailyPayments() {
        System.out.println("Enter date : yyyy-MM-dd");
        LocalDate time = LocalDate.parse(ComponentContainer.scanString.nextLine());
        transactionRepository.dailyPayments(time);
    }

    private void oraliqTolovlar() {
        System.out.println("Enter fromDate : yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(ComponentContainer.scanString.nextLine());
        System.out.println("Enter toDate : yyyy-MM-dd");
        LocalDate toDate = LocalDate.parse(ComponentContainer.scanString.nextLine());
        LinkedList<Transaction> transactions = transactionRepository.oraliqtolovlar(fromDate,toDate);
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    private void generalBalance() {
        cardRepository.companyCardBalance();
    }

    private void transactionByTerminal() {
        System.out.println("Enter code");
        transactionService.transactionByTerminal(ComponentContainer.scanString.nextLine());
    }

    private void transactionByCard() {
        System.out.println("Enter number");
        transactionService.transactionByCard(ComponentContainer.scanString.nextLine());
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
