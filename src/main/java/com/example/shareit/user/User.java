package com.example.shareit.user;

import lombok.Data;
import javax.validation.constraints.Email;

@Data
public class User {

    private long id;
    private String name;
    @Email
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
