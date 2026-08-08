package com.example.userservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return "User registered successfully: " + user.getName();
    }
}
