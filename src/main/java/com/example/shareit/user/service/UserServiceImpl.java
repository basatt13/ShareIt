package com.example.shareit.user.service;

import com.example.shareit.user.User;
import com.example.shareit.user.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    @Override
    public User updateUser(User user, long id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }
}
