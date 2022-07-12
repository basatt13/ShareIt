package com.example.shareit.user;

import com.example.shareit.item.ItemDTO;
import lombok.Data;
import javax.validation.constraints.Email;
import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String name;
    @Email
    private String email;


    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
