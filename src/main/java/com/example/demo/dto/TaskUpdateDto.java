package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TaskUpdateDto {
    private String task_name;
    private String customer;
    private String performer;
    private String description;
    private Long progress;
}

