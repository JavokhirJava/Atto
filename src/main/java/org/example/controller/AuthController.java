package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Profile;
import org.example.status.ProfileStatus;
import org.example.util.MD5Util;

import static java.time.LocalDateTime.now;

public class AuthController {
    public void start() {
        boolean b = true;
        while (b) {
            switch (menu()) {
                case 1 -> logIn();
                case 2 -> registration();
                default -> b = false;
            }
        }
    }
    public Integer menu() {

        System.out.println("  Menu   ");
        System.out.println("1.Login ");
        System.out.println("2.Registration ");

        System.out.println("Enter action :");
        Integer n = ComponentContainer.scanInteger.nextInt();
        return n;
    }
    public void logIn() {
        System.out.println("Enter phone :");
        String phone = ComponentContainer.scanString.nextLine();
        System.out.println("Enter password :");
        String password = ComponentContainer.scanString.nextLine();
        ComponentContainer.authService.login(phone, MD5Util.encode(password));
    }
    public void registration() {
        System.out.println("Enter name ");
        String name = ComponentContainer.scanString.nextLine();
        System.out.println("Enter surname ");
        String surname = ComponentContainer.scanString.nextLine();
        System.out.println("Enter phone ");
        String phone = ComponentContainer.scanString.nextLine();
        System.out.println("Enter password ");
        String password = ComponentContainer.scanString.nextLine();

        Profile profile = new Profile(name, surname, phone, MD5Util.encode(password), now(), ProfileStatus.BLOCKED, "USER");
        ComponentContainer.authService.registration(profile);
    }
}
