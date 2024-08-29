package com.example.demo.mapper;

import com.example.demo.dto.TaskDto;
import com.example.demo.model.Task;
import org.springframework.scheduling.support.ScheduledTaskObservationDocumentation;

public class TaskMapper {
    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getCreated_at(),
                task.getDescription(),
                task.getCustomer(),
                task.getPerformer(),
                task.getProgress(),
                task.getStatus(),
                task.getTask_name(),
                task.getTime_spent()
        );
    }
    public static Task mapToTask(TaskDto task){
        return new Task(
                task.getId(),
                task.getCreated_at(),
                task.getDescription(),
                task.getCustomer(),
                task.getPerformer(),
                task.getProgress(),
                task.getStatus(),
                task.getTask_name(),
                task.getTime_spent()
        );
    }
}
