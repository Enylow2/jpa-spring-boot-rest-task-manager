package com.example.demo.controller;

import com.example.demo.dto.ActivityDto;
import com.example.demo.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks/activities")
public class ActivityController {
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activity){
        ActivityDto createdActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<String> updateActivity(@RequestBody ActivityDto activity)
    {
        activityService.updateActivity(activity);
        return new ResponseEntity<>("Activity id " + activity.getId() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long activityId)
    {
        activityService.deleteActivity(activityId);
        return new ResponseEntity<>("Activity id: " + activityId + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ActivityDto>> getTaskById(@PathVariable("id") Long taskId){
        List<ActivityDto> array = activityService.getTasks(taskId);
        return new ResponseEntity<>(array, HttpStatus.OK);
    }
}
