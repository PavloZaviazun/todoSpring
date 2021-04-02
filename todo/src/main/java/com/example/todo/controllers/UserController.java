package com.example.todo.controllers;

import com.example.todo.dao.UserDAO;
import com.example.todo.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSessionEvent;

@RestController
@AllArgsConstructor
public class UserController {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public void login() {
//        System.out.println(username);
//        System.out.println(password);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println(user);
//        System.out.println(username);
//        System.out.println(password);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);

    }
}
