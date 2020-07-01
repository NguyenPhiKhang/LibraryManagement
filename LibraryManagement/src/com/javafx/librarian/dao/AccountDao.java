package com.javafx.librarian.dao;

import com.javafx.librarian.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private static AccountDao instance;

    private AccountDao() {
    }

    public static AccountDao getInstance() {
        if (instance == null) {
            instance = new AccountDao();
        }
        return instance;
    }

    public List<Account> getAllUsers() {
        List<Account> users = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "select * from tbaccount";

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Account user = new Account(rs.getString("idaccount"), rs.getString("password"), rs.getInt("idper"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public void addUser(Account user) {
        Connection connection = JDBCConnection.getJDBCConnection();

//        String sql = "INSERT INTO USER(Username, Password, Email) VALUES (?,?,?)";
        String sql = "INSERT INTO tbaccount(idaccount, password, idper) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getIdper());

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Account getUser(String username, String password) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM tbaccount WHERE idaccount=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Account(rs.getString("idaccount"), rs.getString("Password"), rs.getInt("idper"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getUserById(String username) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM tbaccount WHERE idaccount=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Account(rs.getString("idaccount"), rs.getString("Password"), rs.getInt("idper"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkCreateUser(String username, String email) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM tbaccount WHERE idaccount=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            rs.last();
            return !(rs.getRow() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int editUser(Account user) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbaccount set password=? where idaccount=?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}