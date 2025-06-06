package com.task1.taskmanager.repository;

import com.task1.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface TaskRepository extends MongoRepository<Task, String> {
    
    // Custom method to find tasks by partial name match (case insensitive)
    List<Task> findByNameContainingIgnoreCase(String name);
}
