package com.example.shareit.request;

import com.example.shareit.UserUt;
import com.example.shareit.user.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemRequestDTO {
    private long id;
    private String description;
    private UserUt requestor;
    private LocalDate created;

    public ItemRequestDTO(long id, String description, UserUt requestor, LocalDate created) {
        this.id = id;
        this.description = description;
        this.requestor = requestor;
        this.created = created;
    }
}
