package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.AdminService;

public class AdminController {
    private CardController cardController;
    private TerminalController terminalController;
    private ProfileController profileController;
    private TransactionController transactionController;

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
        System.out.println("1.Card Menu ");
        System.out.println("2.Terminal Menu ");
        System.out.println("3.Profile Menu ");
        System.out.println("4.Transaction Menu ");
        System.out.println("Enter action :");
        return ComponentContainer.scanInteger.nextInt();
    }

    public void cardMenu() {
        cardController.start();
    }

    public void terminalMenu() {
        terminalController.start();
    }

    public void profileMenu() {
        profileController.start();
    }

    public void transactionMenu() {
        transactionController.start();
    }

    public void setCardController(CardController cardController) {
        this.cardController = cardController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }
}
