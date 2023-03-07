package org.example.database;

import org.example.container.ComponentContainer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static void createTableProfile() {
        try {
            String profile = "create table if not exists profile( " +
                    "                name varchar(25) not null, " +
                    "                surname varchar(25) not null, " +
                    "                phone varchar primary key, " +
                    "                pswd varchar not null, " +
                    "                created_date timestamp not null," +
                    "                status varchar not null," +
                    "                role varchar not null" +
                    "         );";
            Connection con = ComponentContainer.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profile);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableCard() {
        try {
            String cardtable = "create table if not exists card( " +
                    "                phone varchar(25), " +
                    "                number varchar(25) primary key, " +
                    "                exp_date varchar(10), " +
                    "                created_date timestamp not null, " +
                    "                status varchar(25) not null, " +
                    "                balance numeric, " +
                    "                foreign key (phone) references profile(phone)" +
                    "         );";
            Connection con = ComponentContainer.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(cardtable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableTerminal() {
        try {
            String terminaltable = "create table if not exists terminal( " +
                    "                code varchar(25) primary key,  " +
                    "                status varchar(25) not null, " +
                    "                address varchar(25) not null, " +
                    "                created_date timestamp not null" +
                    "         );";
            Connection con = ComponentContainer.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(terminaltable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableTransaction() {
        try {
            String transactiontable = "create table if not exists Transaction( " +
                    "                cardnumber varchar(25) not null,  " +
                    "                amount numeric not null, " +
                    "                terminalNumber varchar(25) not null, " +
                    "                type varchar(25) not null, " +
                    "                transactionDate timestamp," +
                    "                foreign key (cardnumber) references card(number)," +
                    "                foreign key (terminalNumber) references terminal(code)" +
                    "         );";
            Connection con = ComponentContainer.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(transactiontable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
