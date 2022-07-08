package com.example.shareit.user.repository;

import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.user.User;

import java.util.List;

public interface UserRepository {

    List<User> allUsers();

    User addUser(User user) throws DataNotFoundException;

    User getUser(long id);

    User updateUser(User user, long id);

    void deleteUser(long id);
}
