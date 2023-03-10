package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Transaction;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionRepository.saveTransaction(transaction);
        } else System.err.println("error!!!!!!!!!!");
    }

    public void transactionList() {
        transactionRepository.transactionList().stream().forEach(System.out::println);
    }

    public void companyCardBalance() {
       cardRepository.companyCardBalance();
    }

    public void todayPayments() {
        transactionRepository.todayPayments();
    }

    public void dailyPayments() {
        transactionRepository.dailyPayments(LocalDate.now());
    }

    public void oraliqTolovlar(LocalDate fromDate, LocalDate toDate) {
        transactionRepository.oraliqtolovlar(fromDate, toDate);
    }

    public void generalBalance() {
        cardRepository.companyCardBalance();
    }

    public void transactionByTerminal(String code) {
        List<Transaction> transactions = transactionRepository.getTransactionByTerminal(code);
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    public void transactionByCard(String number) {
        List<Transaction> transactions = transactionRepository.getTransactionByCard(number);
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
}
