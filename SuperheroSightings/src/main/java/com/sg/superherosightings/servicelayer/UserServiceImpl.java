/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.dao.UserDao;
import com.sg.superherosightings.model.User;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int userID, String userName) {
        userDao.deleteUser(userID, userName);
    }

    @Override
    public User getUserByID(int userID) {
        return userDao.getUserByID(userID);
    }

}
