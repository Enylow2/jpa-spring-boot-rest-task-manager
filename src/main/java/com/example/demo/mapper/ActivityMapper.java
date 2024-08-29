package com.example.demo.mapper;

import com.example.demo.dto.ActivityDto;
import com.example.demo.model.Activity;

public class ActivityMapper {
    public static ActivityDto mapToActivityDto(Activity activity)
    {
        return new ActivityDto(activity.getId(), activity.getActivity(), activity.getTime(), activity.getAddition_date(), activity.getTaskId());
    }

    public static Activity mapToActivity(ActivityDto activity)
    {
        return new Activity(activity.getId(), activity.getActivity(), activity.getTime(), activity.getAddition_date(), activity.getTaskId());
    }
}
