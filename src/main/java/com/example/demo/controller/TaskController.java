package com.example.demo.controller;

import com.example.demo.dto.StatusDto;
import com.example.demo.dto.TaskDto;
import com.example.demo.dto.TaskUpdateDto;
import com.example.demo.service.TaskService;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDto task)
    {
       TaskDto savedTask = taskService.createTask(task);
       return new ResponseEntity<>("{id:"+savedTask.getId()+"}", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks()
    {
        List<TaskDto> taskList = taskService.getAllTasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId, @RequestBody TaskUpdateDto updatedTask)
    {
        TaskDto newTask = taskService.updateTask(taskId, updatedTask);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId)
    {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task id: " + taskId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> setTaskStatus(@RequestBody StatusDto taskStatus)
    {
        taskService.setTaskStatus(taskStatus.getId(), taskStatus);
        return new ResponseEntity<>("Task id: " + taskStatus.getId() + " has been updated to " + taskStatus.getStatus(), HttpStatus.OK);
    }
}
