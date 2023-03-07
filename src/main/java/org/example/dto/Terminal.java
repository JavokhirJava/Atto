package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.status.TerminalStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class Terminal {
    private String code;
    private String address;
    private TerminalStatus status = TerminalStatus.BLOCKED;
    private LocalDateTime created_date;

    @Override
    public String toString() {
        return "Terminal{" +
                "code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", created_date=" + created_date +
                '}';
    }
}
//code,address,status,created_date