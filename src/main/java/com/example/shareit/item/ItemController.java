package com.example.shareit.item;

import com.example.shareit.item.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Validated
public class ItemController {
    private final ItemServiceImpl itemService;

    @PostMapping
    public ItemDTO addItems(@RequestHeader("X-Sharer-User-Id") long userId,
                            @Valid @RequestBody ItemDTO item) {
        return itemService.addItem(item, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDTO updateItem(@RequestHeader("X-Sharer-User-Id") long userId,
                              @PathVariable long itemId,
                              @Valid @RequestBody ItemDTO item) {
        return itemService.update(item, userId, itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDTO getItemById(@PathVariable long itemId) {
        return itemService.getItem(itemId);
    }

    @GetMapping
    public List<ItemDTO> getAllItems(@RequestHeader("X-Sharer-User-id") long userId) {
        return itemService.getItemsByUserId(userId);
    }

    @GetMapping("/search")
    public List<ItemDTO> getItemByText(@RequestParam String text) {
        return itemService.getItemByText(text);
    }
}
