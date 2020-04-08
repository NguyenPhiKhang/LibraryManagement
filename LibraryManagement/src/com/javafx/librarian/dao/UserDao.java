package com.javafx.librarian.dao;

import com.javafx.librarian.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "select * from USER";

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User(rs.getString("Username"), rs.getString("Password"), rs.getString("Email"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public void addUser(User user) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO USER(Username, Password, Email) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM USER WHERE Username=? and Password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("Username"), rs.getString("Password"), rs.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkCreateUser(String username, String email) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM USER WHERE Username=? or Email=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            ResultSet rs = preparedStatement.executeQuery();

            rs.last();
            return !(rs.getRow() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
