package com.smartBrace.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private String id;  // 任务 ID
    private Integer patientId;
    private Integer doctorId;
    private String actionId;  // 动作 ID
    private String status; // PENDING or COMPLETED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 构造函数
    public Task() {
        this.id = UUID.randomUUID().toString();  // 默认生成一个 UUID 作为任务 ID
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
