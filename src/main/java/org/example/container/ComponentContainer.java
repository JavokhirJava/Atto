package org.example.container;

import org.example.controller.*;
import org.example.dto.Profile;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.example.service.*;
import org.example.status.ProfileStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ComponentContainer {
    public static final Double TOLL =1400.0 ;

    public static void createAdmin(String name, String surname, String phone, String password){
        Profile profile1 = new Profile();
        profile1.setName(name);
        profile1.setSurName(surname);
        profile1.setPhone(phone);
        profile1.setPassword(password);
        profile1.setStatus(ProfileStatus.ACTIVE);
        profile1.setRole("Admin");
        profile1.setCreated_date(LocalDateTime.now());
        pRepository.registration(profile1);
        System.out.println("Admin added successfully !");
    }
    public static String generalBalance = "98606004015730810327";
    public static Profile profile = new Profile();
    public static Scanner scanInteger = new Scanner(System.in);
    public static Scanner scanString = new Scanner(System.in);
    public static Scanner scanDouble = new Scanner(System.in);
    public static UserController uController = new UserController();
    public static AdminController aController = new AdminController();
    public static CardController cardController = new CardController();
    public static TerminalController terminalController = new TerminalController();
    public static ProfileController profileController = new ProfileController();
    public static TransactionController transactionController = new TransactionController();
    public static UserService uService = new UserService();
    public static AdminService aService = new AdminService();
    public static ProfileService pService = new ProfileService();
    public static CardService cardService = new CardService();
    public static AuthService authService = new AuthService();
    public static TerminalService terminalService = new TerminalService();
    public static TransactionService transactionService = new TransactionService();
    public static CardRepository cRepository = new CardRepository();
    public static ProfileRepository pRepository = new ProfileRepository();
    public static TerminalRepository terminalRepository = new TerminalRepository();
    public static TransactionRepository transactionRepository=new TransactionRepository();

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/atto", "postgres", "Java2001");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
