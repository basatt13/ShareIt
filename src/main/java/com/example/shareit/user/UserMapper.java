package com.example.shareit.user;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }

    public static User toUser (UserDTO userDTO){

        User user = new User(userDTO.getName(), userDTO.getEmail());
        user.setId(userDTO.getId());
        return user;
    }
}
