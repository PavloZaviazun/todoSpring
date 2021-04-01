package com.example.todo.controllers;

import com.example.todo.dao.UserDAO;
import com.example.todo.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public void login(@RequestParam String username, @RequestParam String password) {
        System.out.println("MAPPPTINg");
//        System.out.println(username);
//        System.out.println(password);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println(user);
//        System.out.println(username);
//        System.out.println(password);

    }
}
