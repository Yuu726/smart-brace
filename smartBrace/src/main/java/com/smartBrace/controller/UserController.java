package com.smartBrace.controller;

import com.smartBrace.model.ResponseMessage;
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

    // 新增：根据昵称搜索用户
    @GetMapping("/searchByNickname")
    public Object searchByNickname(@RequestParam("nickname") String nickname) {
        Integer userId = userService.findUserIdByNickname(nickname);

        if (userId != null) {
            // 返回一个结构化的 JSON 格式，方便前端解析
            ResponseMessage response = new ResponseMessage();
            response.setUserId(userId);
            response.setMessage("用户已找到");
            return response;  // 返回一个包含用户ID和消息的对象
        } else {
            // 返回失败消息
            ResponseMessage response = new ResponseMessage();
            response.setMessage("未找到匹配的用户");
            return response;
        }
    }
}