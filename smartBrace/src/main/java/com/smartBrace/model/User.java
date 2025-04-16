package com.smartBrace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private Integer id;

    @JsonProperty("phone_number")  // 确保与数据库字段对应
    private String phoneNumber;  // 手机号码作为账号
    private String password;
    private String role; // DOCTOR or PATIENT
    private String nickname;  // 新增字段，表示用户昵称

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
