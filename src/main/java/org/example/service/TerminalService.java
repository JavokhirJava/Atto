package org.example.service;

import org.example.container.ComponentContainer;
import org.example.repository.TerminalRepository;
import org.example.status.TerminalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;
    public void createTerminal(String code, String address) {
        terminalRepository.createTerminal(code, address);
    }
    public void terminalList() {
        terminalRepository.terminalList().forEach(System.out::println);
    }
    public void deleteTerminal() {
        System.out.println("Enter terminal code : ");
        String code = ComponentContainer.scanString.nextLine();
        if (terminalRepository.getTerminal(code) == null) {
            System.err.println("Bunday terminal mavjud emas !");
        } else
            terminalRepository.deleteTerminal(code);
    }
    public void updateTerminal(String code, String address) {
        if (terminalRepository.getTerminal(code) == null) {
            System.out.println("Bunday terminal mavju emas !");
        } else {
            terminalRepository.updateTerminal(code, address);
        }
    }
    public void changeStatusTerminal(String code) {
        if (terminalRepository.getTerminal(code) == null) {
            System.out.println("Nimadur xato ketdi !");
        } else if (terminalRepository.getTerminal(code).getStatus().toString().equals(TerminalStatus.ACTIVE.toString())) {
            terminalRepository.changeStatusTerminal(TerminalStatus.BLOCKED.toString(), code);
        } else if (terminalRepository.getTerminal(code).getStatus().toString().equals(TerminalStatus.BLOCKED.toString())) {
            terminalRepository.changeStatusTerminal(TerminalStatus.ACTIVE.toString(), code);
        }
    }
}
