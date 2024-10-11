package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public User getUser(Principal principal) {
        return userDao.getUserByPrincipal(principal);
    }

    @PutMapping("/user")
    public void updateUserPhone(Principal principal, @RequestParam String phoneNumber) {
        userDao.updateUserPhoneNumber(principal, phoneNumber);
    }

}
