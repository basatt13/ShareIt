package com.example.shareit.user;

import com.example.shareit.exception.DataNotFoundException;
import com.example.shareit.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping
    User addUser(@Valid @RequestBody User user) throws DataNotFoundException {
        return userService.addUser(user);
    }

    @PatchMapping("/{userId}")
    User updateUser(@Valid @RequestBody User user,
                    @PathVariable long userId) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
