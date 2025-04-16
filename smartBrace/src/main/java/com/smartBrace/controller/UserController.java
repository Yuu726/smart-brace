package com.smartBrace.controller;

import com.smartBrace.model.User;
import com.smartBrace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean result = userService.register(user);
        if (result) {
            return "注册成功";
        } else {
            return "手机号已存在";
        }
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User result = userService.login(user.getPhoneNumber(), user.getPassword());
        return result != null ? result : "手机号或密码错误";
    }
}