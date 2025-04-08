package com.smartBrace.service;

import com.smartBrace.model.User;

public interface UserService {
    boolean register(User user);
    User login(String username, String password);

    User findById(Integer doctorId);
}
