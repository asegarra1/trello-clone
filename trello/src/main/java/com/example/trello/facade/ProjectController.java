package com.example.trello.facade;

import com.example.trello.facade.ProjectManagerFacade;
import com.example.trello.model.Task;
import com.example.trello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
Just like the other file in this package exceept for controllers. All requests will just be put through /project
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectManagerFacade projectManagerFacade;

    @Autowired
    public ProjectController(ProjectManagerFacade projectManagerFacade) {
        this.projectManagerFacade = projectManagerFacade;
    }

    // Task commands
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = projectManagerFacade.createTask(task.getDescription(), task.getAssignedTo().getId(), task.getComments());
        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        projectManagerFacade.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}/state")
    public ResponseEntity<Task> updateTaskState(@PathVariable Long id) {
        Task updatedTask = projectManagerFacade.updateTaskState(id);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/tasks/{id}/user")
    public ResponseEntity<Task> assignUserToTask(@PathVariable Long id, @RequestBody Map<String, Long> userId) {
        Long uID = userId.get("userId");
        Task updatedTask = projectManagerFacade.assignUserToTask(id, uID);
        return ResponseEntity.ok(updatedTask);
    }

    // User commands
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = projectManagerFacade.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        projectManagerFacade.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Authentication commands
    @PostMapping("/auth/login")
    public ResponseEntity<Boolean> login(@RequestBody Map<String, String> credentials) {
        boolean success = projectManagerFacade.login(credentials.get("username"), credentials.get("password"));
        return ResponseEntity.ok(success);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        projectManagerFacade.logout();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/auth/authorize")
    public ResponseEntity<Boolean> authorize(@RequestParam String role) {
        boolean authorized = projectManagerFacade.authorize(role);
        return ResponseEntity.ok(authorized);
    }
}