package org.example.controller;

import org.example.container.ComponentContainer;
public class ProfileController {
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
        System.out.println("1.Profile List :");
        System.out.println("2.Change Profile Status :");
        System.out.println("Enter action :");
        Integer n = ComponentContainer.scanInteger.nextInt();
        return n;
    }
    private void profileList() {
        ComponentContainer.aService.profileList();
    }
    private void changeProfileStatus() {
        System.out.println("Enter profile phone :");
        ComponentContainer.aService.changeProfileStatus(ComponentContainer.scanString.nextLine());
    }
}


/**** Menu ***
 1. Login
 Enter phone:
 Enter pswd:
 2. Registration:
 Enter name:
 Enter surname:
 Enter phone:
 Enter pswd:*/