package com.example.shareit.user.repository;

import com.example.shareit.exception.EmailDoubleException;
import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.user.User;
import com.example.shareit.user.UserDTO;
import com.example.shareit.user.UserMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();
    private final List<Long> ids = new ArrayList<>();

    @Override
    public Collection<User> allUsers() {
        return users.values();
    }

    @Override
    public User addUser(UserDTO userDTO) {

        if (userDTO.getEmail() == null) {
            throw new DataNotFoundException("Не указан email пользователя");
        }
        validateEmail(userDTO);
        userDTO.setId(ids.size() + 1);
        User user = UserMapper.toUser(userDTO);
        users.put(user.getId(), user);
        ids.add(user.getId());

        return user;
    }

    @Override
    public User getUser(@NotNull long id) {
        return users.get(id);
    }

    @Override
    public User updateUser(UserDTO user, long id) {
        validateEmail(user);
        if (user.getName() != null && user.getEmail() != null) {
            users.get(id).setEmail(user.getEmail());
            users.get(id).setName(user.getName());
        } else if (user.getName() == null) {
            users.get(id).setEmail(user.getEmail());
        } else if (user.getEmail() == null) {
            users.get(id).setName(user.getName());
        } else {
            return users.get(id);
        }

        return users.get(id);
    }

    @Override
    public void deleteUser(@NotNull long id) {
        users.remove(id);
    }

    public void validateEmail(UserDTO user) {
        for (User u : users.values()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new EmailDoubleException("Пользователь с email:" + user.getEmail() + " уже существует!");
            }
        }
    }
}
