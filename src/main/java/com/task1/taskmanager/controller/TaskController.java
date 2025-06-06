package com.task1.taskmanager.controller;

import com.task1.taskmanager.model.Task;
import com.task1.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /tasks or /tasks?id=123
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(required = false) String id) {
        if (id != null) {
            Task task = taskService.getTaskById(id);
            return (task != null) ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // PUT /tasks (create or update task)
    @PutMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task saved = taskService.saveTask(task);
        return ResponseEntity.ok(saved);
    }

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // GET /tasks/search?name=substring
    @GetMapping("/search")
    public ResponseEntity<?> searchTasks(@RequestParam String name) {
        List<Task> found = taskService.searchTasksByName(name);
        return found.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(found);
    }

    // PUT /tasks/{id}/execute
    @PutMapping("/{id}/execute")
    public ResponseEntity<Task> executeTask(@PathVariable String id) {
        Task updated = taskService.executeTask(id);
        return ResponseEntity.ok(updated);
    }
}
