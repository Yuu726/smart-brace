package com.smartBrace.dao;

import com.smartBrace.model.MotionReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MotionReportMapper {

    // 插入运动报告数据
    @Insert("INSERT INTO motion_report (id, patient_id, action_id, action_name, angle, nickname, timestamp) " +
            "VALUES (#{id}, #{patientId}, #{actionId}, #{actionName}, #{angle}, #{nickname}, #{timestamp})")
    void insertMotionReport(MotionReport motionReport);

    // 根据患者ID查询所有运动报告
    @Select("SELECT * FROM motion_report WHERE patient_id = #{patientId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "actionId", column = "action_id"),
            @Result(property = "actionName", column = "action_name"),
            @Result(property = "angle", column = "angle"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "createdAt", column = "created_at")
    })
    List<MotionReport> getReportsByPatientId(int patientId);

    // 根据运动报告ID查询运动报告
    @Select("SELECT * FROM motion_report WHERE id = #{id}")
    MotionReport getReportById(String id);

}
