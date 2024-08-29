package com.example.demo.service;

import com.example.demo.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto createActivity(ActivityDto activity);
    void updateActivity(ActivityDto activity);
    void deleteActivity(Long activityId);
    List<ActivityDto> getTasks(Long taskId);
}
