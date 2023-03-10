package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private CardController cardController;
    @Autowired
    private TerminalController terminalController;
    @Autowired
    private ProfileController profileController;
    @Autowired
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
}
