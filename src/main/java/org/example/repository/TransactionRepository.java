package org.example.repository;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.status.CardStatus;
import org.example.status.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {
    public void saveTransaction(Transaction transaction) {
        String sql = String.format
                ("insert into transaction (cardnumber,terminalNumber,amount,transactionDate,type)values('%s','%s','%s','%s','%s')",
                        transaction.getCard_number(), transaction.getTerminal_code(), transaction.getAmount(), transaction.getCreated_date(), transaction.getType());
        Connection con = ComponentContainer.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Transaction success update !!!");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> transactionList() {
        List<Transaction> transactionList = new LinkedList<>();

        String sql = String.format("select * from transaction");
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactionDate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalNumber"));
                transaction.setCard_number(resultSet.getString("cardNumber"));
                transactionList.add(transaction);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactionList;
    }

    public void dailyPayments(LocalDate time) {
        List<Transaction> transactionList = new LinkedList<>();

        String sql = String.format("select * from transaction where  date(transactionDate) = date('%s')", time);
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactionDate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalNumber"));
                transaction.setCard_number(resultSet.getString("cardNumber"));
                transactionList.add(transaction);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        transactionList.toString();
    }

    public void todayPayments() {
        List<Transaction> transactionList = new LinkedList<>();

        String sql = String.format("select * from transaction where type = 'PAYMENT' and date(transactionDate) = date(now()) order by transactionDate desc ");

        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactiondate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalnumber"));
                transaction.setCard_number(resultSet.getString("cardnumber"));
                transactionList.add(transaction);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        transactionList.toString();
    }


    public void oraliqtolovlar(LocalDate fromDate, LocalDate toDate) {
        List<Transaction> transactionList = new LinkedList<>();

        String sql = String.format("select * from transaction where  date(transactionDate) >=date('%s') and date(transactionDate) <=date('%s') ", fromDate, toDate);

        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactiondate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalnumber"));
                transaction.setCard_number(resultSet.getString("cardnumber"));
                transactionList.add(transaction);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        transactionList.toString();

    }

    public List<Transaction> getTransactionByTerminal(String code) {
        List<Transaction> transactionList = new LinkedList<>();
        String sql = String.format("select * from transaction where terminalNumber ='%s'", code);
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactionDate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalNumber"));
                transaction.setCard_number(resultSet.getString("cardNumber"));
                transactionList.add(transaction);
            }
            con.close();
            return transactionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public List<Transaction> getTransactionByCard(String number) {
        List<Transaction> transactionList = new LinkedList<>();
        String sql = String.format("select * from transaction where cardNumber ='%s'", number);
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setCreated_date(resultSet.getTimestamp("transactionDate").toLocalDateTime());
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTerminal_code(resultSet.getString("terminalNumber"));
                transaction.setCard_number(resultSet.getString("cardNumber"));
                transactionList.add(transaction);
            }
            con.close();
            return transactionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
