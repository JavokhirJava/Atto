package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.status.ProfileStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class Profile {
    private String name;
    private String surName;
    private String phone;
    private String password;
    private LocalDateTime created_date;
    private ProfileStatus status;
    private String role="USER";

    public Profile(String name, String surName, String phone, String password, LocalDateTime created_date, ProfileStatus status, String role) {
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.password = password;
        this.created_date = created_date;
        this.status = status;
        this.role = role;
    }

    public Profile() {
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", created_date=" + created_date +
                ", status=" + status +
                ", role='" + role + '\'' +
                '}';
    }

    //name,surname,phone unique,pswd,created_date,status,role
}
