package com.example.shareit.request;

import com.example.shareit.UserUt;
class ItemRequestMapper {

    public ItemRequestDTO toItemRequestDTO(ItemRequest itemRequest) {
        UserUt userUt = new UserUt(itemRequest.getRequestor().getId(), itemRequest.getRequestor().getName());

        return new ItemRequestDTO(itemRequest.getId(), itemRequest.getDescription(), userUt, itemRequest.getCreated());

    }
}
