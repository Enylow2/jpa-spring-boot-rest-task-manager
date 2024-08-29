package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ActivityDto {
    private Long id;
    private String activity;
    private float time;
    private Date addition_date = new Date();
    private Long taskId;
}
