package com.example.shareit.item;

import com.example.shareit.UserUt;
import lombok.Builder;
import lombok.Data;

@Data
public class ItemDTO {
    private long id;
    private String name;

    private String description;
    @Builder.Default
    private Boolean available = null;
    private long userID;
    private long request;


    public ItemDTO(String name, String description, Boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }


}
