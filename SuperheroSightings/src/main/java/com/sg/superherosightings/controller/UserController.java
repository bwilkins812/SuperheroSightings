/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.User;
import com.sg.superherosightings.servicelayer.UserService;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brettwilkins
 */
@Controller
public class UserController {

    private UserService userService;
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = userService.getAllUsers();
        model.put("users", users);
        return "displayUserList";
    }

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        newUser.setFirstName(req.getParameter("firstName"));
        newUser.setLastName(req.getParameter("lastName"));
        newUser.setUserName(req.getParameter("userName"));
        String clearPassword = req.getParameter("password");
        String hashPassword = encoder.encode(clearPassword);
        newUser.setPassword(hashPassword);
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        userService.addUser(newUser);

        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request) {

        int userID = Integer.parseInt(request.getParameter("userID"));
        User user = userService.getUserByID(userID);
        String userName = user.getUserName();
        userService.deleteUser(userID, userName);
        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/displayEditUsersForm", method = RequestMethod.GET)
    public String displayEditUserForm(HttpServletRequest request, Model model) {
        int userID = Integer.parseInt(request.getParameter("userID"));
        User user = userService.getUserByID(userID);
        String password = null;
        user.setPassword(password);
        model.addAttribute("user", user);
        return "editUsersForm";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            return "editUsersForm";
        }
        String clearPw = user.getPassword();
        String hashPw = encoder.encode(clearPw);
        user.setPassword(hashPw);
        ArrayList authorities = user.getAuthorities();

        if (authorities.size() == 1) {
            user.addAuthority("ROLE_ADMIN");
        } else {
            user.addAuthority("ROLE_USER");

        }

        userService.updateUser(user);

        return "redirect:displayUserList";
    }
}
