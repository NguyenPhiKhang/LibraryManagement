package com.javafx.librarian.service;

import com.javafx.librarian.dao.UserDao;
import com.javafx.librarian.model.User;

import java.util.List;

public class UserService {
    private static UserService instance;
    private UserService(){}

    public static UserService getInstance(){
        if(instance==null)
            instance = new UserService();
        return instance;
    }

    public List<User> getAllUsers(){
        return UserDao.getInstance().getAllUsers();
    }

    public void addUser(User user){
        UserDao.getInstance().addUser(user);
    }

    public User getUser(String username, String password){
        return UserDao.getInstance().getUser(username, password);
    }

    public boolean checkCreateUser(String username, String email){
        return UserDao.getInstance().checkCreateUser(username, email);
    }
}
