package com.example.shareit.item.service;

import com.example.shareit.item.ItemDTO;
import com.example.shareit.item.repository.ItemRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepositoryImpl itemRepository;

    @Override
    public ItemDTO addItem(ItemDTO itemDTO, long id) {
        return itemRepository.addItem(itemDTO, id);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO, long id, long itemId) {
        return itemRepository.update(itemDTO, id, itemId);
    }

    @Override
    public ItemDTO getItem(long itemId) {
        return itemRepository.getItem(itemId);
    }

    @Override
    public List<ItemDTO> getItemsByUserId(long userId) {
        return itemRepository.getItemsByUserId(userId);
    }

    @Override
    public List<ItemDTO> getItemByText(String text) {
        return itemRepository.getItemByText(text);
    }
}
