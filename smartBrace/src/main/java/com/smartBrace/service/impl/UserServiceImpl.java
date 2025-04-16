package com.smartBrace.service.impl;

import com.smartBrace.dao.UserMapper;
import com.smartBrace.model.User;
import com.smartBrace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {

        // 检查手机号是否已经存在
        if (userMapper.findByPhoneNumber(user.getPhoneNumber()) != null) {
            return false;  // 手机号已存在
        }

        // 如果没有提供昵称，设置默认昵称
        if (user.getNickname() == null || user.getNickname().isEmpty()) {
            user.setNickname(user.getRole() + user.getPhoneNumber());  // 默认昵称
        }

        // 执行数据库插入
        userMapper.insert(user);
        return true;
    }


    @Override
    public User login(String phoneNumber, String password) {
        User user = userMapper.findByPhoneNumber(phoneNumber);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // 登录成功返回用户信息，包括昵称
        }
        return null;  // 登录失败返回 null
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + id);  // 抛出异常，避免空指针
        }
        return user;
    }
}
