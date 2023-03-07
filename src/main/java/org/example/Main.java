package org.example;

import org.example.container.ComponentContainer;
import org.example.controller.AuthController;
import org.example.controller.ProfileController;
import org.example.database.DataBase;
import org.example.util.MD5Util;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // ComponentContainer.createAdmin("Admin","Admin","+777777777777", MD5Util.encode("1111"));
        DataBase.createTableProfile();
        DataBase.createTableCard();
        DataBase.createTableTerminal();
        DataBase.createTableTransaction();

        AuthController authController=new AuthController();
        authController.start();
    }
}