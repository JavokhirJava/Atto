package org.example;

import org.example.controller.AuthController;
import org.example.database.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {
    public static void main(String[] args) {
        // ComponentContainer.createAdmin("Admin","Admin","+777777777777", MD5Util.encode("1111"));
        DataBase.createTableProfile();
        DataBase.createTableCard();
        DataBase.createTableTerminal();
        DataBase.createTableTransaction();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        AuthController authController= (AuthController) context.getBean("authController");
        authController.start();
    }
}