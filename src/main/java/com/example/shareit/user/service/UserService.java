package com.example.shareit.user.service;

import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.user.User;
import com.example.shareit.user.UserDTO;

import java.util.Collection;
import java.util.Map;

public interface UserService {

    Collection<User> allUsers();

    User addUser(UserDTO user) throws DataNotFoundException;

    User getUser(long id);

    User updateUser(UserDTO user, long id);

    void deleteUser(long id);
}
