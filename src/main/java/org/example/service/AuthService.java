package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Profile;

public class AuthService {
    public void login(String phone, String password) {
        if (ComponentContainer.pRepository.login(phone, password) && ComponentContainer.profile.getRole().equals("Admin")) {
            ComponentContainer.aController.start();
        } else if (ComponentContainer.pRepository.login(phone, password)) {
            ComponentContainer.uController.start();
        } else System.out.println(" xato !");
    }
    public void registration(Profile profile) {
        ComponentContainer.pRepository.registration(profile);
        System.out.println("Profile created !!! ");
    }
}
