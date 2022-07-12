package com.example.shareit.item.repository;

import com.example.shareit.item.Item;
import com.example.shareit.item.ItemDTO;
import java.util.List;

interface ItemRepository {

    ItemDTO addItem(ItemDTO itemDTO, long id);

    ItemDTO update(ItemDTO itemDTO, long id, long itemId);

    ItemDTO getItem(long itemId);

    List<ItemDTO> getItemsByUserId(long UserId);

    List<ItemDTO> getItemByText(String text);

}
