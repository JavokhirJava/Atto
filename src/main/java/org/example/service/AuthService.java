package org.example.service;

import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.repository.ProfileRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    ProfileRepository profileRepository;
    AdminController adminController;
    UserController userController;

    public AuthService(ProfileRepository profileRepository, AdminController adminController, UserController userController) {
        this.profileRepository = profileRepository;
        this.adminController = adminController;
        this.userController = userController;
    }

    public void login(String phone, String password) {
        if (profileRepository.login(phone, password) && ComponentContainer.profile.getRole().equals("Admin")) {
            adminController.start();
        } else if (profileRepository.login(phone, password)) {
            userController.start();
        } else System.out.println(" xato !");
    }
    public void registration(Profile profile) {
        profileRepository.registration(profile);
        System.out.println("Profile created !!! ");
    }

}
