package com.smartBrace.service.impl;

import com.smartBrace.dao.MotionReportMapper;
import com.smartBrace.dto.MotionReportDTO;
import com.smartBrace.model.MotionReport;
import com.smartBrace.service.MotionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MotionReportServiceImpl implements MotionReportService {

    @Autowired
    private MotionReportMapper motionReportMapper;

    @Override
    public void saveMotionReport(MotionReportDTO reportDTO) {
//        System.out.println("Received actionId: " + reportDTO.getActionId());

        if (reportDTO.getActionId() == null || reportDTO.getActionId().isEmpty()) {
            throw new IllegalArgumentException("actionId cannot be null or empty");
        }

        MotionReport motionReport = new MotionReport();
        motionReport.setId(UUID.randomUUID().toString());
        motionReport.setPatientId(reportDTO.getPatientId());
        motionReport.setActionId(reportDTO.getActionId());
        motionReport.setActionName(reportDTO.getActionName());
        motionReport.setAngle(reportDTO.getAngle());
        motionReport.setNickname(reportDTO.getNickname());
        motionReport.setTimestamp(reportDTO.getTimestamp());
        motionReport.setCreatedAt(LocalDateTime.now());  // ✅ 新增的设置

        motionReportMapper.insertMotionReport(motionReport);
    }

    @Override
    public List<MotionReport> getReportsByPatientId(int patientId) {
        return motionReportMapper.getReportsByPatientId(patientId);
    }
}
