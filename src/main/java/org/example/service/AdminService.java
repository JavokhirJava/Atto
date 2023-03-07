package org.example.service;

import org.example.container.ComponentContainer;
import org.example.status.ProfileStatus;

public class AdminService {


    public void profileList() {
        ComponentContainer.pRepository.profileList().forEach(System.out::println);
    }

    public void changeProfileStatus(String phone) {
        if (ComponentContainer.pRepository.getStatus(phone) == null) {
            System.out.println("Bunday profile mavjud emas !");
        } else if (ComponentContainer.pRepository.getStatus(phone).equals(ProfileStatus.ACTIVE.toString())) {
            ComponentContainer.pRepository.changeStatusProfile("BLOCKED",phone);
        }else if (ComponentContainer.pRepository.getStatus(phone).equals(ProfileStatus.BLOCKED.toString())){
            ComponentContainer.pRepository.changeStatusProfile("ACTIVE",phone);
        }
    }
}
