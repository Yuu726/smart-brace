package com.smartBrace.controller;

import com.smartBrace.dto.MotionReportDTO;
import com.smartBrace.dto.MotionReportUploadDTO;
import com.smartBrace.model.MotionReport;
import com.smartBrace.service.MotionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motionReport")
public class MotionReportController {

    @Autowired
    private MotionReportService motionReportService;

    // 上传多个运动数据
    @PostMapping("/upload")
    public String uploadMotionReport(@RequestBody MotionReportUploadDTO uploadDTO) {
        for (MotionReportDTO reportDTO : uploadDTO.getData()) {
            reportDTO.setPatientId(uploadDTO.getPatientId());
            reportDTO.setNickname(uploadDTO.getNickname());
            motionReportService.saveMotionReport(reportDTO);
        }
        return "上传成功";
    }

    // 获取患者的运动报告
    @GetMapping("/getReports/{patientId}")
    public List<MotionReport> getReportsByPatientId(@PathVariable int patientId) {
        List<MotionReport> reports = motionReportService.getReportsByPatientId(patientId);

        reports.forEach(report -> {
            String formattedReport = formatMotionReport(report);
            System.out.println(formattedReport);
        });

        return reports;
    }

    private String formatMotionReport(MotionReport report) {
        StringBuilder formattedReport = new StringBuilder();
        formattedReport.append("-------------------------------------------------\n");
        formattedReport.append("患者昵称: ").append(report.getNickname()).append("\n");
        formattedReport.append("运动报告 ID: ").append(report.getId()).append("\n");
        formattedReport.append("运动名称: ").append(report.getActionName()).append("\n");
        formattedReport.append("运动角度: ").append(report.getAngle()).append("°\n");
        formattedReport.append("运动时间: ").append(report.getTimestamp()).append("\n");
        formattedReport.append("-------------------------------------------------\n");
        return formattedReport.toString();
    }
}
