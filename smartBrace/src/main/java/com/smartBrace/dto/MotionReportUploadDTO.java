package com.smartBrace.dto;

import java.util.List;

public class MotionReportUploadDTO {
    private int patientId;
    private String nickname;
    private List<MotionReportDTO> data;

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<MotionReportDTO> getData() {
        return data;
    }

    public void setData(List<MotionReportDTO> data) {
        this.data = data;
    }
}
