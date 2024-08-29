package com.example.demo.service;

import com.example.demo.dto.StatusDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.dto.TaskUpdateDto;
import com.example.demo.model.Task;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto task);
    List<TaskDto> getAllTasks();
    TaskDto updateTask(Long taskId, TaskUpdateDto updatedTask);
    void deleteTask(Long taskId);
    void setTaskStatus(Long taskId, StatusDto taskStatus);

}
