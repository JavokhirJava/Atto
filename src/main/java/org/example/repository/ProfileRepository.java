package org.example.repository;

import org.example.container.ComponentContainer;
import org.example.dto.Profile;
import org.example.status.ProfileStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProfileRepository {
    public boolean login(String phone, String password) {
        Connection con = ComponentContainer.getConnection();
        boolean b = false;
        String sql = String.format("select * from profile where phone ='%s' and pswd ='%s'", phone, password);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ComponentContainer.profile = getProfile(phone, password);
                b = true;
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    public void registration(Profile profile) {
        Connection con = ComponentContainer.getConnection();
        String sql = "Insert into profile  (name,surname,phone,pswd,created_date,status,role) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurName());
            preparedStatement.setString(3, profile.getPhone());
            preparedStatement.setString(4, profile.getPassword());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(profile.getCreated_date()));
            preparedStatement.setString(6, String.valueOf(profile.getStatus()));
            preparedStatement.setString(7, profile.getRole());
            preparedStatement.execute();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profile> profileList() {
        List<Profile> profileList = new LinkedList<>();
        Connection con = ComponentContainer.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("Select * from profile ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setName(resultSet.getString("name"));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setPhone(resultSet.getString("phone"));
                profile.setRole(resultSet.getString("role"));
                profile.setPassword(resultSet.getString("pswd"));
                profile.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                profile.setSurName(resultSet.getString("surname"));
                profileList.add(profile);
            }
            con.close();
            return profileList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profile getProfile(String phone, String password) {
        Connection con = ComponentContainer.getConnection();
        Profile profile = new Profile();
        boolean b = false;
        String sql = String.format("select * from profile where phone ='%s' and pswd ='%s'", phone, password);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile.setName(resultSet.getString("name"));
                profile.setSurName(resultSet.getString("surname"));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setPhone(resultSet.getString("phone"));
                profile.setRole(resultSet.getString("role"));
                profile.setPassword(resultSet.getString("pswd"));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profile;
    }

    public String getStatus(String phone) {
        Connection con = ComponentContainer.getConnection();
        Profile profile = new Profile();
        boolean b = false;
        String sql = String.format("select * from profile where phone ='%s'", phone);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                return profile.getStatus().toString();
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profile.getStatus().toString();
    }

    public void changeStatusProfile(String status, String phone) {
        Connection con = ComponentContainer.getConnection();
        String sql = String.format("update profile set status = '%s' where phone = '%s' ", status, phone);
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

}//name,surname,phone unique,pswd,created_date,status,role
