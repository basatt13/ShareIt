package com.example.shareit.booking;

import com.example.shareit.item.Item;
import com.example.shareit.user.User;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Booking {
    private long id;
    private LocalDate start;
    private LocalDate end;
    private Item item;
    private User booker;
    private Status status;

    enum Status {
        WAITING,
        APPROVED,
        REJECTED,
        CANCELED
    }

    public Booking(long id, LocalDate start, LocalDate end, Item item, User booker, Status status) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.item = item;
        this.booker = booker;
        this.status = status;
    }


}
