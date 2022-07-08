package com.example.shareit.user;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }
}
