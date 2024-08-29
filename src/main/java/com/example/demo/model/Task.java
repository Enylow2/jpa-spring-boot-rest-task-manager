package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at", columnDefinition = "date default current_date")
    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();

    @Column(name = "customer")
    private String customer;

    @Column(name = "performer")
    private String performer;

    @Column(name = "progress")
    private Long progress = 0L;

    @Column(name = "status", columnDefinition = "varchar(255) default 'Created'")
    private String status = "Created";

    @Column(name = "task_name")
    private String task_name;

    @Column(name = "time_spent", columnDefinition = "float default 0")
    private float time_spent = 0f;
}
