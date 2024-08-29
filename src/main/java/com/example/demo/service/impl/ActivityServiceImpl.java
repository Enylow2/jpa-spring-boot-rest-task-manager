package com.example.demo.service.impl;

import com.example.demo.dto.ActivityDto;
import com.example.demo.dto.ActivityUpdateDto;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Activity;
import com.example.demo.model.Task;
import com.example.demo.repo.ActivityRepo;
import com.example.demo.repo.TaskRepo;
import com.example.demo.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepo activityRepo;
    private TaskRepo taskRepo;
    @Override
    public ActivityDto createActivity(ActivityDto activity) {
        Task foundTask = taskRepo.findById(activity.getTaskId()).orElseThrow(
                () -> new ResourceNotFoundException("Task with id: " + activity.getTaskId() + " doesn't exist")
        );
        foundTask.setTime_spent(foundTask.getTime_spent()+activity.getTime());
        taskRepo.save(foundTask);
        Activity createdActivity = ActivityMapper.mapToActivity(activity);
        activityRepo.save(createdActivity);
        return ActivityMapper.mapToActivityDto(createdActivity);
    }

    @Override
    public void updateActivity(ActivityDto activity) {
        Task foundTask = taskRepo.findById(activity.getTaskId()).orElseThrow(
                () -> new ResourceNotFoundException("Task with id: " + activity.getId() + " doesn't exist")
        );
        Activity foundActivity = activityRepo.findById(activity.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Activity with id: " + activity.getId() + " doesn't exist")
        );
        foundTask.setTime_spent(foundTask.getTime_spent() - foundActivity.getTime());
        foundTask.setTime_spent(foundTask.getTime_spent() + activity.getTime());
        taskRepo.save(foundTask);
        foundActivity.setActivity(activity.getActivity());
        foundActivity.setTime(activity.getTime());
        activityRepo.save(foundActivity);
    }

    @Override
    public void deleteActivity(Long activityId) {

        Activity foundActivity = activityRepo.findById(activityId).orElseThrow(
                () -> new ResourceNotFoundException("Activity with id: " + activityId + " doesn't exist")
        );
        Task foundTask = taskRepo.findById(foundActivity.getTaskId()).orElseThrow(
                () -> new ResourceNotFoundException("Task with id: " + foundActivity.getTaskId() + " doesn't exist")
        );
        foundTask.setTime_spent(foundTask.getTime_spent() - foundActivity.getTime());
        taskRepo.save(foundTask);
        activityRepo.delete(foundActivity);
    }

    @Override
    public List<ActivityDto> getTasks(Long taskId) {
        taskRepo.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with id: " + taskId + " doesn't exist")
        );

        List<Activity> activities = activityRepo.findByTaskId(taskId);
        return activities.stream().map((activity) -> ActivityMapper.mapToActivityDto(activity))
                .collect(Collectors.toList());
    }
}
