package com.example.shareit.item;

import com.example.shareit.request.ItemRequest;
import com.example.shareit.user.User;
import lombok.Builder;
import lombok.Data;

@Data
public class Item {
    private long id;
    private String name;
    private String description;
    private boolean available;
    private User owner;


    @Builder.Default
    private ItemRequest request = null;

    public Item(long id, String name, String description, boolean available, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.owner = owner;
    }
}
