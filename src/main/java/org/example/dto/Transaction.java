package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.status.TransactionType;

import java.time.LocalDateTime;
@Getter
@Setter
public class Transaction {
    private String card_number;
    private Double amount;
    private String terminal_code;
    private TransactionType type; /// REFILL ,AMOUNT
    private LocalDateTime created_date;

    public Transaction() {
    }

    public Transaction(String card_number, Double amount, String terminal_code, TransactionType type, LocalDateTime created_date) {
        this.card_number = card_number;
        this.amount = amount;
        this.terminal_code = terminal_code;
        this.type = type;
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "card_number='" + card_number + '\'' +
                ", amount=" + amount +
                ", terminal_code='" + terminal_code + '\'' +
                ", type='" + type + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
//card_number,amount,terminal_code,type,created_date