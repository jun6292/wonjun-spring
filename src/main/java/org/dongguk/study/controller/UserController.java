package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.service.UserService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public UserDto read(@PathVariable Long id) {
        return userService.readUserProfile(id);
    }

    @PutMapping("/user/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        return userService.updateUserProfile(id, dto);
    }

    @DeleteMapping("/user/{id}")
    public UserDto delete(@PathVariable Long id) {
        return userService.deleteUserProfile(id);
    }
}
