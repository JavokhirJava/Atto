package org.example.service;

import org.example.container.ComponentContainer;
import org.example.repository.ProfileRepository;
import org.example.status.ProfileStatus;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
    private ProfileRepository profileRepository;

    public AdminService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void profileList() {
        profileRepository.profileList().forEach(System.out::println);
    }

    public void changeProfileStatus(String phone) {
        if (profileRepository.getStatus(phone) == null) {
            System.out.println("Bunday profile mavjud emas !");
        } else if (profileRepository.getStatus(phone).equals(ProfileStatus.ACTIVE.toString())) {
            profileRepository.changeStatusProfile("BLOCKED",phone);
        }else if (profileRepository.getStatus(phone).equals(ProfileStatus.BLOCKED.toString())){
            profileRepository.changeStatusProfile("ACTIVE",phone);
        }
    }
//
//    public void setProfileRepository(ProfileRepository profileRepository) {
//        this.profileRepository = profileRepository;
//    }
}
