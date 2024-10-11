package com.techelevator.dao;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.RoleDto;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    User getUserByPrincipal(Principal principal);

    void updateUserPhoneNumber(Principal principal, String phoneNumber);

}
