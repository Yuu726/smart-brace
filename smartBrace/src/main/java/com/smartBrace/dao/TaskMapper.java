package com.smartBrace.dao;

import com.smartBrace.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO task(id, patient_id, doctor_id, action_id, status) VALUES(#{id}, #{patientId}, #{doctorId}, #{actionId}, 'PENDING')")
    void assignTask(Task task);

    @Select("SELECT * FROM task WHERE patient_id = #{patientId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "doctorId", column = "doctor_id"),
            @Result(property = "actionId", column = "action_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Task> getTasksByPatientId(Integer patientId);

    @Update("UPDATE task SET status = 'COMPLETED' WHERE id = #{taskId}")
    void completeTask(String taskId);

    @Select("SELECT * FROM task WHERE id = #{taskId}")
    Task getTaskById(String taskId);  // 根据任务 ID 查询任务

}

