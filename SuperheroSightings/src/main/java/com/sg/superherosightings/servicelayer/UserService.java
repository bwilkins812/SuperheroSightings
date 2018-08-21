/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.servicelayer;

import com.sg.superherosightings.model.User;
import java.util.List;

/**
 *
 * @author brettwilkins
 */
public interface UserService {

    public void addUser(User newUser);

    public User getUserByID(int userID);

    public List<User> getAllUsers();

    public void deleteUser(int userID, String userName);

    public void updateUser(User user);

}
