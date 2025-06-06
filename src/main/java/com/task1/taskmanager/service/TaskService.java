package com.task1.taskmanager.service;

import com.task1.taskmanager.model.Task;
import com.task1.taskmanager.model.TaskExecution;
import com.task1.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Save a new task after validating the command
    public Task saveTask(Task task) {
        if (containsMaliciousCommand(task.getCommand())) {
            throw new IllegalArgumentException("Command contains unsafe keywords.");
        }
        return taskRepository.save(task);
    }

    // Delete task by ID
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    // Search task by name
    public List<Task> searchTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    // Execute command and store TaskExecution result
    public Task executeTask(String id) {
        Task task = getTaskById(id);
        if (task == null) {
            throw new NoSuchElementException("Task not found");
        }

        try {
            Date startTime = new Date();

            // Run shell command
            Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", task.getCommand()});

            //Process process = Runtime.getRuntime().exec(task.getCommand());

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.lines().collect(Collectors.joining("\n"));

            process.waitFor();
            Date endTime = new Date();

            // Create new execution record
            TaskExecution execution = new TaskExecution(startTime, endTime, output);
            task.getTaskExecutions().add(execution);

            return taskRepository.save(task);

        } catch (Exception e) {
            throw new RuntimeException("Command execution failed: " + e.getMessage());
        }
    }

    // Security: Block dangerous shell commands
    private boolean containsMaliciousCommand(String command) {
        String[] blacklist = {"rm", "shutdown", "reboot", "kill", "format", "mkfs", "dd", ":(){ :|: & };:"};
        for (String banned : blacklist) {
            if (command.toLowerCase().contains(banned)) {
                return true;
            }
        }
        return false;
    }
}
