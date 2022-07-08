package com.example.shareit.item;

import com.example.shareit.user.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemMapper {

    private static UserRepositoryImpl userRepository;

    public static ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(item.getName(), item.getDescription(), item.isAvailable());
        itemDTO.setUserID(item.getOwner().getId());
        if (item.getRequest() != null) {
            itemDTO.setRequest(item.getRequest().getId());
        }
        itemDTO.setAvailable(itemDTO.getAvailable());
        return itemDTO;
    }

    public static Item toItem(ItemDTO itemDTO, long userId) {
        return new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getDescription(), itemDTO.getAvailable(),
                userRepository.getUser(userId));
    }
}
