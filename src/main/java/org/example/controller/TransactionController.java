package org.example.controller;

import org.example.container.ComponentContainer;

import java.time.LocalDate;

public class TransactionController {
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
        System.out.println("Transaction Menu :");
        System.out.println("1.Transaction List :");
        System.out.println("2.Company Card Balance :");
        System.out.println("3.Bugungi to'lovlar :");
        System.out.println("4.Kunlik to'lovlar :");
        System.out.println("5.Oraliq to'lovlar :");
        System.out.println("6.Umumiy balance :");
        System.out.println("7.Transaction by Terminal :");
        System.out.println("8.Transaction By Card :");
        System.out.println("Enter action :");
        return ComponentContainer.scanInteger.nextInt();
    }

    private void transactionList() {
        ComponentContainer.transactionService.transactionList();
    }

    private void companyCardBalance() {
        ComponentContainer.cRepository.companyCardBalance();
    }

    private void todayPayments() {
        ComponentContainer.transactionRepository.todayPayments();
        System.out.println("Enter date");
    }

    private void dailyPayments() {
        System.out.println("Enter date : yyyy-MM-dd");
        LocalDate time = LocalDate.parse(ComponentContainer.scanString.nextLine());
        ComponentContainer.transactionRepository.dailyPayments(time);
    }

    private void oraliqTolovlar() {
        System.out.println("Enter fromDate : yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(ComponentContainer.scanString.nextLine());
        System.out.println("Enter toDate : yyyy-MM-dd");
        LocalDate toDate = LocalDate.parse(ComponentContainer.scanString.nextLine());
        ComponentContainer.transactionRepository.oraliqtolovlar(fromDate,toDate);

    }

    private void generalBalance() {
        ComponentContainer.cRepository.companyCardBalance();
    }

    private void transactionByTerminal() {
        System.out.println("Enter code");
        ComponentContainer.transactionService.transactionByTerminal(ComponentContainer.scanString.nextLine());

    }

    private void transactionByCard() {
        System.out.println("Enter number");
        ComponentContainer.transactionService.transactionByTerminal(ComponentContainer.scanString.nextLine());
    }
}
