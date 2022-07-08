package com.example.shareit.item.repository;

import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.exception.NotFoundIdException;
import com.example.shareit.item.ItemDTO;
import com.example.shareit.user.repository.UserResources;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {


    @Override
    public ItemDTO addItem(ItemDTO itemDTO, long id) {
        if (!UserResources.users.keySet().contains(id)) {
            throw new NotFoundIdException("Нет пользователя с указанным id:" + id);
        }
        if (itemDTO.getAvailable() == null || itemDTO.getDescription() == null || itemDTO.getName().equals("")) {
            throw new DataNotFoundException("Некорректные данные запроса");
        }
        itemDTO.setId(ItemsResources.items.size() + 1);
        itemDTO.setUserID(id);
        ItemsResources.items.add(itemDTO);
        return itemDTO;
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO, long id, long itemId) {
        ItemDTO itemDTO1 = null;
        if (itemDTO.getAvailable() != null && itemDTO.getName() == null && itemDTO.getDescription() == null) {
            for (ItemDTO i : ItemsResources.items) {
                if (itemId == i.getId()) {
                    if (i.getUserID() != id) {
                        throw new NotFoundIdException("Некорректные данные пользователя");
                    } else {
                        i.setAvailable(itemDTO.getAvailable());
                        i.setUserID(id);
                        itemDTO1 = i;
                    }
                }
            }
        } else if (itemDTO.getAvailable() == null && itemDTO.getName() != null && itemDTO.getDescription() == null) {
            for (ItemDTO i : ItemsResources.items) {
                if (itemId == i.getId()) {
                    if (i.getUserID() != id) {
                        throw new NotFoundIdException("Некорректные данные пользователя");
                    } else {
                        i.setName(itemDTO.getName());
                        i.setUserID(id);
                        itemDTO1 = i;
                    }
                }
            }
        } else if (itemDTO.getAvailable() == null && itemDTO.getName() == null && itemDTO.getDescription() != null) {
            for (ItemDTO i : ItemsResources.items) {
                if (itemId == i.getId()) {
                    if (i.getUserID() != id) {
                        throw new NotFoundIdException("Некорректные данные пользователя");
                    } else {
                        i.setDescription(itemDTO.getDescription());
                        i.setUserID(id);
                        itemDTO1 = i;
                    }
                }
            }
        } else {
            for (ItemDTO i : ItemsResources.items) {
                if (itemId == i.getId()) {
                    if (i.getUserID() != id) {
                        throw new NotFoundIdException("Некорректные данные пользователя");
                    } else {
                        i.setName(itemDTO.getName());
                        i.setDescription(itemDTO.getDescription());
                        i.setAvailable(itemDTO.getAvailable());
                        i.setRequest(itemDTO.getRequest());
                        i.setUserID(id);
                        itemDTO1 = i;
                    }
                }
            }
        }
        return itemDTO1;
    }

    @Override
    public ItemDTO getItem(long itemId) {
        ItemDTO itemDTO = null;
        for (ItemDTO i : ItemsResources.items) {
            if (i.getId() == itemId) {
                itemDTO = i;
            }
        }
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getItemsByUserId(long userId) {
        List<ItemDTO> itemList = new ArrayList<>();
        for (ItemDTO i : ItemsResources.items) {
            if (i.getUserID() == userId) {
                itemList.add(i);
            }
        }
        return itemList;
    }

    @Override
    public List<ItemDTO> getItemByText(String text) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        if (text.equals("")) {
            return itemDTOList;
        } else {
            for (ItemDTO i : ItemsResources.items) {
                if (i.getDescription().toLowerCase().contains(text.toLowerCase()) ||
                        i.getName().toLowerCase().contains(text.toLowerCase())) {
                    if (i.getAvailable()) {
                        itemDTOList.add(i);
                    }
                }
            }
            return itemDTOList;
        }
    }
}
