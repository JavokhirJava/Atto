package org.example.service;

import org.example.container.ComponentContainer;
import org.example.status.TerminalStatus;

public class TerminalService {
    public void createTerminal(String code, String address) {
        ComponentContainer.terminalRepository.createTerminal(code, address);
    }
    public void terminalList() {
        ComponentContainer.terminalRepository.terminalList().forEach(System.out::println);
    }
    public void deleteTerminal() {
        System.out.println("Enter terminal code : ");
        String code = ComponentContainer.scanString.nextLine();
        if (ComponentContainer.terminalRepository.getTerminal(code) == null) {
            System.err.println("Bunday terminal mavjud emas !");
        } else
            ComponentContainer.terminalRepository.deleteTerminal(code);
    }
    public void updateTerminal(String code, String address) {
        if (ComponentContainer.terminalRepository.getTerminal(code) == null) {
            System.out.println("Bunday terminal mavju emas !");
        } else {
            ComponentContainer.terminalRepository.updateTerminal(code, address);
        }
    }
    public void changeStatusTerminal(String code) {
        if (ComponentContainer.terminalRepository.getTerminal(code) == null) {
            System.out.println("Nimadur xato ketdi !");
        } else if (ComponentContainer.terminalRepository.getTerminal(code).getStatus().toString().equals(TerminalStatus.ACTIVE.toString())) {
            ComponentContainer.terminalRepository.changeStatusTerminal(TerminalStatus.BLOCKED.toString(), code);
        } else if (ComponentContainer.terminalRepository.getTerminal(code).getStatus().toString().equals(TerminalStatus.BLOCKED.toString())) {
            ComponentContainer.terminalRepository.changeStatusTerminal(TerminalStatus.ACTIVE.toString(), code);
        }
    }
}
