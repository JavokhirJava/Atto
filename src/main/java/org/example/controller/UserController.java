package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Profile;

public class UserController {
    public void start() {
        boolean b = true;
        while (b) {
            switch (userMenu()) {
                case 1 -> addCard();
                case 2 -> cardList();
                case 3 -> cchStatus();
                case 4 -> deleteCard();
                case 5 -> refill();
                case 6 -> transaction();
                case 7 -> makePayment();
                default -> b = false;
            }
        }
    }

    private void makePayment() {
        System.out.println("Enter cardNumber :");
        String number = ComponentContainer.scanString.nextLine();
        System.out.println("Enter Terminal Code :");
        String code = ComponentContainer.scanString.nextLine();
        ComponentContainer.uService.makePayment(number,code,ComponentContainer.profile);
    }

    private void transaction() {
        ComponentContainer.uService.transaction(ComponentContainer.cRepository.cardListByPhone(ComponentContainer.profile));
    }

    private void refill() {
        System.out.println("Enter Card Number :");
        String number = ComponentContainer.scanString.nextLine();
        System.out.println("Enter Terminal code :");
        String code = ComponentContainer.scanString.nextLine();
        System.out.println("Enter balanced ");
        Double balance = ComponentContainer.scanDouble.nextDouble();
        ComponentContainer.uService.refill(number, code, balance);
    }

    private void deleteCard() {
        System.out.println("Enter card Number :");
        ComponentContainer.uService.deleteCard(ComponentContainer.scanString.nextLine(), ComponentContainer.profile);
    }

    private void cchStatus() {
        System.out.println("Enter card Number : ");
        ComponentContainer.uService.changeCardStatus(ComponentContainer.scanString.nextLine(), ComponentContainer.profile);
    }

    private void cardList() {
        ComponentContainer.uService.cardList(ComponentContainer.profile);
    }

    private void addCard() {
        System.out.println("Enter card number :");
        String card = ComponentContainer.scanString.nextLine();
        ComponentContainer.uService.addCard(card, ComponentContainer.profile);
    }

    public Integer userMenu() {
        System.out.println("**** User Menu ****");
        System.out.println("1.Add Card :");
        System.out.println("2.Card List :");
        System.out.println("3.Card Change Status :");
        System.out.println("4.Delete Card :");
        System.out.println("5.Refill :");
        System.out.println("6.Transaction :");
        System.out.println("7.Make Payment :");
        return ComponentContainer.scanInteger.nextInt();
    }
}
/**** User  Menu **
 (Card)
 1. Add Card (number) - (cartani profile ga ulab qo'yamiz.) (added_date)
 Enter Card Number:
 (kiritilgan number database da bo'lishi kerak.)
 2. Card List (number,exp_date,balance)
 3. Card Change Status
 4. Delete Card (visible_user = false,deleted_user)

 4. ReFill (pul tashlash) (carta userga tegishli bo'lishi kerak.)
 Enter card number:
 Balance:
 (Transaction with type 'ReFill')

 (Transaction)
 5. Transaction
 CardNumber, Address, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)
 6. Make Payment (pul to'lash)*/