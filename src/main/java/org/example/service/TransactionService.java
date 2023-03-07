package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            ComponentContainer.transactionRepository.saveTransaction(transaction);
        } else System.err.println("error!!!!!!!!!!");
    }

    public void transactionList() {
        ComponentContainer.transactionRepository.transactionList().stream().forEach(System.out::println);
    }

    public void companyCardBalance() {
        ComponentContainer.cRepository.companyCardBalance();
    }

    public void todayPayments() {
        ComponentContainer.transactionRepository.todayPayments();
    }

    public void dailyPayments() {
        ComponentContainer.transactionRepository.dailyPayments(LocalDate.now());
    }

    public void oraliqTolovlar(LocalDate fromDate, LocalDate toDate) {
        ComponentContainer.transactionRepository.oraliqtolovlar(fromDate, toDate);
    }

    public void generalBalance() {
        ComponentContainer.cRepository.companyCardBalance();
    }

    public void transactionByTerminal(String code) {
        List<Transaction> transactions = ComponentContainer.transactionRepository.getTransactionByTerminal(code);
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    public void transactionByCard(String number) {
        List<Transaction> transactions = ComponentContainer.transactionRepository.getTransactionByCard(number);
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
}
