package com.example.trello.controller;

import com.example.trello.facade.ProjectManagerFacade;
import com.example.trello.model.Task;
import com.example.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;

/*
Handles all http requesets for everything associated with Tasks.
Works directly with TaskService
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task.getDescription(), task.getAssignedTo().getId(), task.getComments());

        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAll() {
        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/user")
    public ResponseEntity<Void> removeAssignedUsers(@PathVariable Long id) {
        taskService.removeUsers(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/comment")
    public ResponseEntity<Void> removeComments(@PathVariable Long id) {
        taskService.removeComments(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<Task> getAll() {
        return taskService.showAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.showById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<Task> updateTaskState(@PathVariable Long id) {
        Task updatedTask = taskService.updateState(id);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<Task> addUser(@PathVariable Long id, @RequestBody Map<String, Long> userId) {
        Long uID = userId.get("userId");
        Task updatedTask = taskService.assignUser(id, uID);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<Task> addComment(@PathVariable Long id, @RequestBody Map<String, String> comment ) {
        String text = comment.get("comment");
        Task updatedTask = taskService.addComment(id, text);
        return ResponseEntity.ok(updatedTask);
    }



    
}