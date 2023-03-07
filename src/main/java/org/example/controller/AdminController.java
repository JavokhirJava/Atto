package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.AdminService;

public class AdminController {
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> cardMenu();
                case 2 -> terminalMenu();
                case 3 -> profileMenu();
                case 4 -> transactionMenu();
                default -> b = false;
            }
        }
    }

    public Integer menu() {
        System.out.println("*** Admin Menu ***");
        System.out.println("1.Card Menu :");
        System.out.println("2.Terminal Menu :");
        System.out.println("3.Profile Menu :");
        System.out.println("4.Transaction Menu :");
        System.out.println("Enter action :");
        return ComponentContainer.scanInteger.nextInt();
    }

    public void cardMenu() {
        ComponentContainer.cardController.start();
    }

    public void terminalMenu() {
        ComponentContainer.terminalController.start();
    }

    public void profileMenu() {
        ComponentContainer.profileController.start();
    }

    public void transactionMenu() {
        ComponentContainer.transactionController.start();
    }

}
