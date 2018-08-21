/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.mappers.UserMapper;
import com.sg.superherosightings.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brettwilkins
 */
public class UserDaoJdbcTemplateImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_USER
            = "insert into users (firstName, lastName, userName, password, enabled) values (?, ?, ?, ?, 1)";
    private static final String SQL_UPDATE_USER
            = "update users set firstName = ?, lastName = ?, userName = ?, password = ?, enabled = 1 where userID = ?";
    private static final String SQL_INSERT_AUTHORITY
            = "insert into authorities (userName, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
            = "delete from users where userID = ?";
    private static final String SQL_DELETE_AUTHORITIES
            = "delete from authorities where userName = ?";
    private static final String SQL_SELECT_USER
            = "select * from users where userID = ?";
    private static final String SQL_GET_ALL_USERS
            = "select * from users";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER,
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getUserName(),
                newUser.getPassword());
        newUser.setUserID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    newUser.getUserName(),
                    authority);
        }

        return newUser;
    }

    @Override
    public void deleteUser(int userID, String userName) {

        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, userName);
        jdbcTemplate.update(SQL_DELETE_USER, userID);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {

        int userID = user.getUserID();
        User formerUser = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userID);
        String userName = formerUser.getUserName();
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, userName);
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getPassword(),
                user.getUserID());

        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    user.getUserName(),
                    authority);
        }
    }

    @Override
    public User getUserByID(int userID) {

        User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userID);
        return user;

    }
}
