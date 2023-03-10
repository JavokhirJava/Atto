package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class ProfileController {
    private AdminService aService;
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()){
                case 1->profileList();
                case 2->changeProfileStatus();
                default -> b=false;
            }
        }
    }
    public Integer menu() {
        System.out.println("1.Profile List ");
        System.out.println("2.Change Profile Status ");
        System.out.println("Enter action :");
        Integer n = ComponentContainer.scanInteger.nextInt();
        return n;
    }
    private void profileList() {
        aService.profileList();
    }
    private void changeProfileStatus() {
        System.out.println("Enter profile phone :");
        aService.changeProfileStatus(ComponentContainer.scanString.nextLine());
    }

    public void setaService(AdminService aService) {
        this.aService = aService;
    }
}


