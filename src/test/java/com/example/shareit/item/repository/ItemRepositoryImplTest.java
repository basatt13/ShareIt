package com.example.shareit.item.repository;

import com.example.shareit.item.ItemDTO;
import com.example.shareit.user.UserDTO;
import com.example.shareit.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemRepositoryImplTest {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    ItemRepositoryImplTest(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    @Test
    void addItem() {
        ItemDTO itemDTO = new ItemDTO("name","description",true);
        itemDTO.setUserID(1);
        userRepository.addUser(new UserDTO("name","denja25@yandex.ru"));
        itemRepository.addItem(itemDTO,1);
        Assertions.assertTrue(true);
    }

    @Test
    void update() {
    }

    @Test
    void getItem() {
    }

    @Test
    void getItemsByUserId() {
    }

    @Test
    void getItemByText() {
    }
}