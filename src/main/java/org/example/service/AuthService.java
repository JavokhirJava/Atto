package org.example.service;

import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.repository.ProfileRepository;

public class AuthService {
    ProfileRepository pRepository;
    AdminController aController;
    UserController uController;
    public void login(String phone, String password) {
        if (pRepository.login(phone, password) && ComponentContainer.profile.getRole().equals("Admin")) {
            aController.start();
        } else if (pRepository.login(phone, password)) {
            uController.start();
        } else System.out.println(" xato !");
    }
    public void registration(Profile profile) {
        pRepository.registration(profile);
        System.out.println("Profile created !!! ");
    }

    public void setpRepository(ProfileRepository pRepository) {
        this.pRepository = pRepository;
    }

    public void setaController(AdminController aController) {
        this.aController = aController;
    }

    public void setuController(UserController uController) {
        this.uController = uController;
    }
}
