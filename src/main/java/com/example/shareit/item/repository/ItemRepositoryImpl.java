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

    private final Map<Long, Item> storage = new LinkedHashMap<>();
    private final Map<Long, Set<Item>> userItemIndex = new LinkedHashMap<>();
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemDTO addItem(ItemDTO itemDTO, long id) {
        if (!userRepository.allUsers().contains(userRepository.getUser(id))) {
            throw new NotFoundIdException("Нет пользователя с указанным id:" + id);
        }
        if (itemDTO.getAvailable() == null || itemDTO.getDescription() == null || itemDTO.getName().equals("")) {
            throw new DataNotFoundException("Некорректные данные запроса");
        }
        itemDTO.setId(storage.size() + 1);
        itemDTO.setUserID(id);
        Item item = itemMapper.toItem(itemDTO, id);
        item.setId(storage.size() + 1);
        storage.put(item.getId(), item);
        if (userItemIndex.containsKey(id)) {
            userItemIndex.get(id).add(item);
        } else {
            Set<Item> itemValues = new HashSet<>();
            itemValues.add(item);
            userItemIndex.put(id, itemValues);
        }
        return itemMapper.toItemDTO(item);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO, long id, long itemId) {
        if (!userItemIndex.containsKey(id)) {
            throw new NotFoundIdException("Некорректные данные пользователя");
        } else {
            if (itemDTO.getAvailable() != null && itemDTO.getName() == null && itemDTO.getDescription() == null) {
                storage.get(itemId).setAvailable(itemDTO.getAvailable());
                for (Item i : userItemIndex.get(id)) {
                    if (i.getId() == itemId) {
                        i = storage.get(itemId);
                    }
                }
            } else if (itemDTO.getAvailable() == null && itemDTO.getName() != null && itemDTO.getDescription() == null) {
                storage.get(itemId).setName(itemDTO.getName());
                for (Item i : userItemIndex.get(id)) {
                    if (i.getId() == itemId) {
                        i = storage.get(itemId);
                    }
                }
            } else if (itemDTO.getAvailable() == null && itemDTO.getName() == null && itemDTO.getDescription() != null) {
                storage.get(itemId).setDescription(itemDTO.getDescription());
                for (Item i : userItemIndex.get(id)) {
                    if (i.getId() == itemId) {
                        i = storage.get(itemId);
                    }
                }
            } else {
                Item oldItem = storage.get(itemDTO.getId());
                oldItem.setId(itemId);
                oldItem.setName(itemDTO.getName());
                oldItem.setDescription(itemDTO.getDescription());
                oldItem.setOwner(userRepository.getUser(id));
                oldItem.setAvailable(itemDTO.getAvailable());
                for (Item i : userItemIndex.get(id)) {
                    if (i.getId() == itemId) {
                        i = oldItem;
                    }
                }
            }
            return itemMapper.toItemDTO(storage.get(itemId));
        }
    }

    @Override
    public ItemDTO getItem(long itemId) {
        return itemMapper.toItemDTO(storage.get(itemId));
    }

    @Override
    public List<ItemDTO> getItemsByUserId(long userId) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item i : userItemIndex.get(userId)) {
            itemDTOList.add(itemMapper.toItemDTO(i));
        }
        return itemDTOList;
    }

    @Override
    public List<ItemDTO> getItemByText(String text) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        if (text.equals("")) {
            return itemDTOList;
        } else {
            for (Item i : storage.values()) {
                if (i.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                        i.getName().toLowerCase().contains(text.toLowerCase())) {
                    if (i.isAvailable()) {
                        itemDTOList.add(itemMapper.toItemDTO(i));
                    }
                }
            }
            return itemDTOList;
        }
    }
}



