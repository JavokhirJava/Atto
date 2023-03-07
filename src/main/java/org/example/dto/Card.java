package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.status.CardStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class Card {
    private String number;
    private String exp_date;
    private Double balance=50.00; ////tiyin uzb=uzb
    private CardStatus status=CardStatus.BLOCK;
    private String phone;
    private LocalDateTime created_date;

    public Card() {
    }
    public Card(String number, String exp_date, Double balance, CardStatus status, String phone, LocalDateTime created_date) {
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
    }
    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", exp_date='" + exp_date + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
//number,exp_date,balance,status,phone,created_date