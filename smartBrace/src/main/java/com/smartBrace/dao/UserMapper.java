package com.smartBrace.dao;

import com.smartBrace.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE phone_number = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Insert("INSERT INTO user(phone_number, password, role, nickname) VALUES(#{phoneNumber}, #{password}, #{role}, #{nickname})")
    void insert(User user);

}
