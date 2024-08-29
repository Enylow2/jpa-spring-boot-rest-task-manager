package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private Date created_at = new Date();
    private String description;
    private String customer;
    private String performer;
    private Long progress = 0L;
    private String status = "Created";
    private String task_name;
    private float time_spent = 0f;
}
