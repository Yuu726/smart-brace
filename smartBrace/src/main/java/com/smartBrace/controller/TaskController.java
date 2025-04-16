package com.smartBrace.controller;

import com.smartBrace.dto.TaskResponseDTO;
import com.smartBrace.model.Task;
import com.smartBrace.service.TaskService;
import com.smartBrace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smartBrace.dao.ActionMapper;  // 引入动作表的 Mapper

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActionMapper actionMapper;  // 引入动作表的 Mapper

    @PostMapping("/assign")
    public Map<String, Object> assignTask(@RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();

        // 验证动作是否存在
        var action = actionMapper.findById(task.getActionId());
        if (action == null) {
            response.put("status", "error");
            response.put("message", "错误：指定的动作 ID 不存在。");
            return response;
        }

        // 验证医生
        var doctor = userService.findById(task.getDoctorId());
        if (doctor == null || !"DOCTOR".equals(doctor.getRole())) {
            response.put("status", "error");
            response.put("message", "只能由医生指派任务");
            return response;
        }

        // 验证患者
        var patient = userService.findById(task.getPatientId());
        if (patient == null || !"PATIENT".equals(patient.getRole())) {
            response.put("status", "error");
            response.put("message", "任务只能指派给患者");
            return response;
        }

        // 分配任务
        taskService.assignTask(task);

        // 获取患者和医生的昵称
        String doctorNickname = doctor != null ? doctor.getNickname() : "未知医生";
        String patientNickname = patient != null ? patient.getNickname() : "未知患者";

        response.put("status", "success");
        response.put("message", "任务已成功分配");
        response.put("taskId", task.getId());
        response.put("actionName", action != null ? action.getActionName() : "未知动作");
        response.put("doctorNickname", doctorNickname);
        response.put("patientNickname", patientNickname);

        return response;
    }



    @GetMapping("/list/{patientId}")
    public Map<String, Object> getTasksForPatient(@PathVariable Integer patientId) {
        Map<String, Object> response = new HashMap<>();
        List<Task> tasks = taskService.getTasksByPatientId(patientId);

        if (tasks == null || tasks.isEmpty()) {
            response.put("status", "error");
            response.put("message", "当前患者无任务记录");
            return response;
        }

        List<TaskResponseDTO> result = new ArrayList<>();
        for (Task task : tasks) {
            var action = actionMapper.findById(task.getActionId());

            // 创建 DTO 并填充数据
            TaskResponseDTO dto = new TaskResponseDTO();
            dto.setTaskId(task.getId());
            dto.setActionId(task.getActionId());
            dto.setStatus(task.getStatus());
            dto.setCreatedAt(task.getCreatedAt());

            // 确保actionName不为null
            dto.setActionName(action != null ? action.getActionName() : "未知动作");

            result.add(dto);
        }

        response.put("status", "success");
        response.put("data", result);
        return response;
    }

    @PutMapping("/complete/{taskId}")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable String taskId, @RequestParam Integer patientId) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 校验任务是否存在
            Task task = taskService.getTaskById(taskId);
            if (task == null) {
                response.put("status", "error");
                response.put("message", "任务不存在");
                return ResponseEntity.badRequest().body(response);  // 返回 400 错误
            }

            // 校验是否为患者操作
            if (!task.getPatientId().equals(patientId)) {
                response.put("status", "error");
                response.put("message", "该任务只能由指定患者完成");
                return ResponseEntity.status(403).body(response);  // 返回 403 禁止访问
            }

            // 更新任务状态
            taskService.completeTask(taskId);

            // 获取患者昵称
            var patient = userService.findById(patientId);
            String patientNickname = patient != null ? patient.getNickname() : "未知患者";

            response.put("status", "success");
            response.put("message", "任务已标记为完成");
            response.put("patientNickname", patientNickname);  // 返回患者的昵称
            return ResponseEntity.ok(response);  // 返回 200 成功

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "更新任务状态失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);  // 返回 500 错误
        }
    }



}
