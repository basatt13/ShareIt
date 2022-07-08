package com.example.shareit.user.repository;

import com.example.shareit.exception.EmailDoubleException;
import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.user.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public List<User> allUsers() {
        List<User> allusers = new ArrayList<>();
        for (User u : UserResources.users.values()) {
            allusers.add(u);
        }
        return allusers;
    }

    @Override
    public User addUser(User user) {

        if (user.getEmail() == null) {
            throw new DataNotFoundException("Не указан email пользователя");
        }
        validateEmail(user);
        user.setId(UserResources.ids.size() + 1);
        UserResources.users.put(user.getId(), user);
        UserResources.ids.add(user.getId());

        return user;
    }

    @Override
    public User getUser(long id) {
        return UserResources.users.get(id);
    }

    @Override
    public User updateUser(User user, long id) {
        validateEmail(user);
        if (user.getName() != null && user.getEmail() != null) {
            UserResources.users.get(id).setEmail(user.getEmail());
            UserResources.users.get(id).setName(user.getName());
        } else if (user.getName() == null) {
            UserResources.users.get(id).setEmail(user.getEmail());
        } else if (user.getEmail() == null) {
            UserResources.users.get(id).setName(user.getName());
        } else {
            return UserResources.users.get(id);
        }

        return UserResources.users.get(id);
    }

    @Override
    public void deleteUser(long id) {
        UserResources.users.remove(id);
    }

    public void validateEmail(User user) {
        for (User u : UserResources.users.values()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new EmailDoubleException("Пользователь с email:" + user.getEmail() + " уже существует!");
            }
        }
    }
}
