package com.smartBrace.service;

import com.smartBrace.model.Task;

import java.util.List;

public interface TaskService {
    void assignTask(Task task);
    List<Task> getTasksByPatientId(Integer patientId);
    void completeTask(String taskId);
    Task getTaskById(String taskId);  // 新增获取任务方法

}
