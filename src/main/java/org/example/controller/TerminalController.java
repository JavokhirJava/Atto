package org.example.controller;

import org.example.container.ComponentContainer;

public class TerminalController {
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> createTerminal();
                case 2 -> terminalList();
                case 3 -> updateTerminal();
                case 4 -> changeTerminalStatus();
                case 5 -> delete();
                default -> b = false;
            }
        }
    }

    public Integer menu() {
        System.out.println("1.Create Terminal :");
        System.out.println("2.Terminal List :");
        System.out.println("3.Update Terminal :");
        System.out.println("4.Change Terminal Status :");
        System.out.println("5.Delete Terminal :");
        System.out.println("Enter action :");
        Integer n = ComponentContainer.scanInteger.nextInt();
        return n;
    }

    private void createTerminal() {
        System.out.println("Enter Terminal code :");
        String code = ComponentContainer.scanString.nextLine();
        System.out.println("Enter Terminal Address :");
        String address = ComponentContainer.scanString.nextLine();
        ComponentContainer.terminalService.createTerminal(code, address);
    }

    private void terminalList() {
        ComponentContainer.terminalService.terminalList();
    }

    private void updateTerminal() {
        System.out.println("Enter Terminal code :");
        String code = ComponentContainer.scanString.nextLine();
        System.out.println("Enter Address :");
        String address = ComponentContainer.scanString.nextLine();
        ComponentContainer.terminalService.updateTerminal(code, address);
    }

    private void changeTerminalStatus() {
        System.out.println("Enter terminal code :");
        ComponentContainer.terminalService.changeStatusTerminal(ComponentContainer.scanString.nextLine());
    }

    private void delete() {
        ComponentContainer.terminalService.deleteTerminal();
    }

}
