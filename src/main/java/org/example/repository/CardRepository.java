package org.example.repository;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Transaction;
import org.example.service.TransactionService;
import org.example.status.CardStatus;
import org.example.status.ProfileStatus;
import org.example.status.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Repository
public class CardRepository {
    @Autowired
    private TransactionService transactionService;
    public Integer createCard(String number, String exp_date) {
        String sql = String.format
                ("insert into card (number,exp_date,created_date,status,balance)values('%s','%s',now(),'BLOCK',0)", number, exp_date);
        Connection con = ComponentContainer.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            con.close();
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getCardList() {
        cardList().forEach(System.out::println);
    }
    public List<Card> cardList() {
        List<Card> cardList = new LinkedList<>();

        String sql = String.format("select * from card");
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setPhone(resultSet.getString("phone"));
                card.setNumber(resultSet.getString("number"));
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setBalance(resultSet.getDouble("balance"));
                card.setExp_date(resultSet.getString("exp_date"));
                card.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                cardList.add(card);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cardList;
    }
    public String getStatus(String phone) {
        Connection con = ComponentContainer.getConnection();
        Card card = new Card();
        boolean b = false;
        String sql = String.format("select * from card where phone ='%s'", phone);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                return card.getStatus().toString();
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return card.getStatus().toString();
    }
    public void cChStatus(String status,String phone) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set status = '%s' where phone = '%s' ", status, phone);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.out.println("Status Changed !!! ");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void changePhone(String number, String phone) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set phone = '%s' where number = '%s'", phone, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Exp_date Success Updated !!!");
            } else {
                System.out.println("Exp_date Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Card getCard(String cardNumber) {
        String sql = String.format("select * from card where number ='%s'", cardNumber);
        Card card = new Card();
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            if (resultSet.next()) {
                card.setPhone(resultSet.getString("phone"));
                card.setNumber(cardNumber);
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setBalance(resultSet.getDouble("balance"));
                card.setExp_date(resultSet.getString("exp_date"));
                card.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
            }
            con.close();
            return card;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCard(String cardNumber) {

        if (getCard(cardNumber) == null) {
            System.out.println("Bunday karta mavuj emas !");
            return;
        }
        String sql = String.format("delete from card where number ='%s'", cardNumber);
        Connection con = ComponentContainer.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int n = statement.executeUpdate();
            if (n > 0) {
                System.err.println("Deleted Card Access !");
            } else {
                System.err.println("Error");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCard(String number, String expDate) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set exp_date = '%s' where number = '%s'", expDate, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Exp_date Success Updated !!!");
            } else {
                System.out.println("Exp_date Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCardToPhone(String number, String phone) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set phone = '%s' where number = '%s'", phone, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Card Success added !!!");
            } else {
                System.out.println("Card Not added");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Card> cardListByPhone(Profile profile) {
        return cardList().stream().filter(card -> card.getPhone()!=null && card.getPhone().equals(profile.getPhone())).collect(Collectors.toList());
    }

    public void changeStatusCardByCardNumber(String cardNumber,String status) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set status = '%s' where number = '%s' ", status, cardNumber);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.out.println("Status Changed !!! ");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void refill(String number,String code, Double balance,Card card ) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", balance+card.getBalance(), number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Balance Success Updated !!!");
                transactionService.addTransaction(new Transaction(number,balance,code, TransactionType.REFILL, LocalDateTime.now()));
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void makePayment(String number,String code, Double balance,Card card){
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", card.getBalance()-balance, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                generalBalance(ComponentContainer.generalBalance,balance);
                System.err.println("Balance Success Updated !!!");
               transactionService.addTransaction(new Transaction(number,balance,code, TransactionType.PAYMENT, LocalDateTime.now()));
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void generalBalance(String number,Double balance){
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", getCard(number).getBalance()+balance, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Balance Success Updated !!!");
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void companyCardBalance(){
        System.out.println(getCard(ComponentContainer.generalBalance).getBalance());
    }

}
