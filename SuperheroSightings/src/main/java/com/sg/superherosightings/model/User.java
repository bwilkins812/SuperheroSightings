/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.ArrayList;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brettwilkins
 */
public class User {

    private int userID;

    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 20, message = "First Name must be no more than 20 characters in length.")
    private String firstName;

    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 20, message = "Last Name must be no more than 20 characters in length.")
    private String lastName;

    @NotEmpty(message = "You must supply a value for User Name.")
    @Length(max = 20, message = "User Name must be no more than 20 characters in length.")
    private String userName;

    @NotEmpty(message = "You must supply a value for password.")
    @Length(max = 20, message = "Your password must be no more than 20 characters in length.")
    private String password;

    private ArrayList<String> authorities = new ArrayList<>();

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        authorities.add(authority);
    }

}
