package com.example.shareit.user;

import com.example.shareit.item.Item;
import com.example.shareit.item.ItemDTO;
import lombok.Data;
import javax.validation.constraints.Email;
import java.util.List;

@Data
public class User {

    private long id;
    private String name;

    private String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
