package com.example.shareit.item;

import com.example.shareit.user.User;
import com.example.shareit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
@RequiredArgsConstructor
public class ItemMapper {

    private final UserRepository userRepository;


    public ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(item.getName(), item.getDescription(), item.isAvailable());
        itemDTO.setUserID(item.getOwner().getId());
        if (item.getRequest() != null) {
            itemDTO.setRequest(item.getRequest().getId());
        }
        itemDTO.setAvailable(itemDTO.getAvailable());
        itemDTO.setId(item.getId());
        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO, long userId) {
        return new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getDescription(), itemDTO.getAvailable(),
             userRepository.getUser(userId));
    }
}
