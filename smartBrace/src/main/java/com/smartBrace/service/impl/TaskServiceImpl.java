package com.smartBrace.service.impl;

import com.smartBrace.dao.TaskMapper;
import com.smartBrace.model.Task;
import com.smartBrace.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void assignTask(Task task) {
        taskMapper.assignTask(task);
    }

    @Override
    public List<Task> getTasksByPatientId(Integer patientId) {
        return taskMapper.getTasksByPatientId(patientId);
    }

    @Override
    public Task getTaskById(String taskId) {
        return taskMapper.getTaskById(taskId);  // 调用 Mapper 方法获取任务
    }

    @Override
    public void completeTask(String taskId) {
        taskMapper.completeTask(taskId);
    }
}
