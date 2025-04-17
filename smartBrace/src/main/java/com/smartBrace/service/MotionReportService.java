package com.smartBrace.service;

import com.smartBrace.dto.MotionReportDTO;
import com.smartBrace.model.MotionReport;

import java.util.List;

public interface MotionReportService {
    // 保存运动报告
    void saveMotionReport(MotionReportDTO reportDTO);

    // 根据患者ID获取所有运动报告
    List<MotionReport> getReportsByPatientId(int patientId);
}
