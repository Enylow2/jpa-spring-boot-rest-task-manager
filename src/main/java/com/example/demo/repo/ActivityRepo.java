package com.example.demo.repo;

import com.example.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActivityRepo extends JpaRepository<Activity, Long> {
    List<Activity> findByTaskId(Long taskId);
    void deleteByIdIn(List<Long> ids);
}
