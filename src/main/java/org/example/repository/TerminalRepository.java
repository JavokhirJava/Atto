package org.example.repository;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Terminal;
import org.example.status.CardStatus;
import org.example.status.TerminalStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class TerminalRepository {
    public Integer createTerminal(String code, String address) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format
                ("Insert into terminal (code,address,created_date,status) values ('%s','%s',now(),'%s')", code, address, TerminalStatus.ACTIVE);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            con.close();
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Terminal> terminalList() {
        List<Terminal> terminalList = new LinkedList<>();

        String sql = String.format("select * from terminal");
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                Terminal terminal = new Terminal();
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                terminalList.add(terminal);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return terminalList;
    }

    public void deleteTerminal(String code) {

        if (getTerminal(code) == null) {
            System.out.println("Bunday karta mavuj emas !");
            return;
        }
        String sql = String.format("delete from terminal where code ='%s'", code);
        Connection con = ComponentContainer.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int n = statement.executeUpdate();
            if (n > 0) {
                System.err.println("Deleted Card Access !");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Terminal getTerminal(String code) {
        String sql = String.format("select * from terminal where code ='%s'", code);
        Terminal terminal = new Terminal();
        Connection con = ComponentContainer.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            if (resultSet.next()) {
                terminal.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
            }
            con.close();
            return terminal;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTerminal(String code, String address) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update terminal set address = '%s' where code = '%s' ", address, code);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Address Successfully Updated !");
            } else {
                System.out.println("Address Not Updated !");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeStatusTerminal(String status, String code) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update terminal set status = '%s' where code = '%s' ", status, code);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Status Successfully Updated !");
            } else {
                System.out.println("Status Not Updated !");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
///code,address,status,created_date
