package com.example.shareit.booking;

import com.example.shareit.UserUt;
import com.example.shareit.request.ItemRequest;
import com.example.shareit.user.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private long id;
    private LocalDate start;
    private LocalDate end;
    private ItemUt item;
    private UserUt booker;
    private String status;

    public BookingDTO(long id, LocalDate start, LocalDate end, ItemUt itemUt, UserUt userUt, String status) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.item = item;
        this.booker = booker;
        this.status = status;
    }


    @Data
    static class ItemUt {
        private long id;
        private String name;
        private String description;
        private boolean available;
        private UserUt owner;

        public ItemUt(long id, String name, String description, boolean available, UserUt owner) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.available = available;
            this.owner = owner;
        }
    }

}
