package com.example.shareit.item.repository;

import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.exception.NotFoundIdException;
import com.example.shareit.item.Item;
import com.example.shareit.item.ItemDTO;
import com.example.shareit.item.ItemMapper;
import com.example.shareit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> storage = new HashMap<>();
    private final Map<Long, Set<Item>> userItemIndex = new HashMap<>();
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemDTO addItem(ItemDTO itemDTO, long id) {
        if (!userRepository.allUsers().contains(userRepository.getUser(id))) {
            throw new NotFoundIdException("Нет пользователя с указанным id:" + id);
        }
        if (itemDTO.getAvailable() == null || itemDTO.getDescription() == null || itemDTO.getName().isBlank()) {
            throw new DataNotFoundException("Некорректные данные запроса");
        }
        itemDTO.setId(storage.size() + 1);
        itemDTO.setUserID(id);
        Item item = itemMapper.toItem(itemDTO, id);
        storage.put(item.getId(), item);
        if (userItemIndex.containsKey(id)) {
            userItemIndex.get(id).add(item);
        } else {
            Set<Item> itemSet = new HashSet<>();
            itemSet.add(storage.get(itemDTO.getId()));
            userItemIndex.put(id, itemSet);
        }
        return itemMapper.toItemDTO(item);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO, long id, long itemId) {
        if (!storage.containsKey(itemId) || !userItemIndex.containsKey(id) ||
                storage.get(itemId).getOwner().getId() != id) {
            throw new NotFoundIdException("Некорректные данные пользователя");
        }
        if (itemDTO.getAvailable() != null) {
            storage.get(itemId).setAvailable(itemDTO.getAvailable());
        }
        if (itemDTO.getName() != null) {
            storage.get(itemId).setName(itemDTO.getName());
        }

        if (itemDTO.getDescription() != null) {
            storage.get(itemId).setDescription(itemDTO.getDescription());
        }
        storage.get(itemId).setId(itemId);
        storage.get(itemId).setOwner(userRepository.getUser(id));
        return itemMapper.toItemDTO(storage.get(itemId));
    }

    @Override
    public ItemDTO getItem(long itemId) {
        return itemMapper.toItemDTO(storage.get(itemId));
    }

    @Override
    public Set<Item> getItemsByUserId(long userId) {
        return userItemIndex.get(userId);
    }

    @Override
    public List<ItemDTO> getItemByText(String text) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        if (!text.isBlank()) {
            for (Item i : storage.values()) {
                if (i.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                        i.getName().toLowerCase().contains(text.toLowerCase())) {
                    if (i.isAvailable()) {
                        itemDTOList.add(itemMapper.toItemDTO(i));
                    }
                }
            }
        }
        return itemDTOList;
    }
}



