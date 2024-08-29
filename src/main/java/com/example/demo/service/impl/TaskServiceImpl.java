package com.example.demo.service.impl;

import com.example.demo.dto.StatusDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.dto.TaskUpdateDto;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Activity;
import com.example.demo.model.Task;
import com.example.demo.repo.ActivityRepo;
import com.example.demo.repo.TaskRepo;
import com.example.demo.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;
    private ActivityRepo activityRepo;
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        System.out.println(taskRepo.findAll().size());
        if(taskRepo.findAll().size() <= 25) {
            Task task = TaskMapper.mapToTask(taskDto);
            Task savedTask = taskRepo.save(task);
            return TaskMapper.mapToTaskDto(savedTask);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Task limit exceeded");
        }
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream().map((task) -> TaskMapper.mapToTaskDto(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskUpdateDto updatedTask) {
        Task task =  taskRepo.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Task with id: " + taskId + " doesn't exist")
        );
        if(updatedTask.getProgress() <= 100 && updatedTask.getProgress() >= 0) {
            task.setTask_name(updatedTask.getTask_name());
            task.setCustomer(updatedTask.getCustomer());
            task.setProgress(updatedTask.getProgress());
            task.setPerformer(updatedTask.getPerformer());
            task.setDescription(updatedTask.getDescription());
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Parameter progress must be in 0 to 100 range");
        }

        Task updatedTaskObject =  taskRepo.save(task);
        return TaskMapper.mapToTaskDto(updatedTaskObject);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        List<Activity> activity =  activityRepo.findByTaskId(taskId);
        Task task =  taskRepo.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Task with id: " + taskId + " doesn't exist")
        );
        List<Long> idsToDelete = activity.stream().map((el) -> el.getId()).toList();
        activityRepo.deleteByIdIn(idsToDelete);
        taskRepo.delete(task);
    }

    @Override
    public void setTaskStatus(Long taskId, StatusDto taskStatus) {
        Task task =  taskRepo.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Task with id: " + taskId + " doesn't exist")
        );
        task.setStatus(taskStatus.getStatus());
        taskRepo.save(task);
    }

}
