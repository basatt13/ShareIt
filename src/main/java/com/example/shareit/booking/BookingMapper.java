package com.example.shareit.booking;

import com.example.shareit.UserUt;
import com.example.shareit.item.Item;

public class BookingMapper {

    public BookingDTO toBookingDTO(Booking booking) {
        Item item = booking.getItem();
        UserUt userUt = new UserUt(booking.getBooker().getId(), booking.getBooker().getName());
        BookingDTO.ItemUt itemUt = new BookingDTO.ItemUt(item.getId(), item.getName(), item.getDescription(),
                item.isAvailable(), userUt);

        return new BookingDTO(booking.getId(), booking.getStart(), booking.getEnd(), itemUt, userUt,
                booking.getStatus().toString());
    }
}
