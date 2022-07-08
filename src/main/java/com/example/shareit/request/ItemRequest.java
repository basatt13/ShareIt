package com.example.shareit.request;

import com.example.shareit.user.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemRequest {
    private long id;
    private String description;
    private User requestor;
    private LocalDate created;
}
