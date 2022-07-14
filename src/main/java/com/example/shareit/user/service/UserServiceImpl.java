package com.example.shareit.user.service;

import com.example.shareit.user.User;
import com.example.shareit.user.UserDTO;
import com.example.shareit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;

    @Override
    public Collection<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public User addUser(UserDTO user) {
        return userRepository.addUser(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    @Override
    public User updateUser(UserDTO user, long id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }
}
