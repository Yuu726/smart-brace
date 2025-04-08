package com.smartBrace.dao;

import com.smartBrace.model.Action;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActionMapper {

    @Select("SELECT * FROM action WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "actionName", column = "action_name"),
            @Result(property = "createdAt", column = "created_at")
    })
    Action findById(String id);  // 查询动作是否存在
}
