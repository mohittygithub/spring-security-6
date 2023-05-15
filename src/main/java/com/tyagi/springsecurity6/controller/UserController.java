package com.tyagi.springsecurity6.controller;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.User;
import com.tyagi.springsecurity6.record.UserRecord;
import com.tyagi.springsecurity6.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String sayHello() {
        return "Hello World.";
    }


    @PostMapping("/create")
    public ApiResponse create(@RequestBody User user) {
        return userService.create(user);
    }
}
