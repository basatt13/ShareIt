package com.example.shareit.user;

import com.example.shareit.user.service.UserService;
import com.example.shareit.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    Collection<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    User addUser(@Valid @RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @PatchMapping("/{userId}")
    User updateUser(@Valid @RequestBody UserDTO user,
                    @PathVariable long userId) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
