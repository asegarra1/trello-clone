package com.example.trello.facade;

import com.example.trello.model.Comment;
import com.example.trello.model.Task;
import com.example.trello.model.User;
import com.example.trello.service.AuthService;
import com.example.trello.service.TaskService;
import com.example.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/*
A facade class to combine all the services of auth, task, and user on one page for a more centralized point,
particularly in terms of design, maintainability, and ease of use.
 */
@Component
public class ProjectManagerFacade {

    private final TaskService taskService;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public ProjectManagerFacade(TaskService taskService, UserService userService, AuthService authService) {
        this.taskService = taskService;
        this.userService = userService;
        this.authService = authService;
    }

    // Task-related methods
    public Task createTask(String description, Long userId, List<Comment> comments) {
        return taskService.createTask(description, userId, comments);
    }

    public void deleteTask(Long taskId) {
        taskService.deleteTask(taskId);
    }

    public void deleteAllTasks() {
        taskService.deleteAll();
    }

    public Task updateTaskState(Long taskId) {
        return taskService.updateState(taskId);
    }

    public Task assignUserToTask(Long taskId, Long userId) {
        return taskService.assignUser(taskId, userId);
    }

    public void removeAssignedUsersFromTask(Long taskId) {
        taskService.removeUsers(taskId);
    }

    public Task addCommentToTask(Long taskId, String commentText) {
        return taskService.addComment(taskId, commentText);
    }

    public Task removeCommentsFromTask(Long taskId) {
        return taskService.removeComments(taskId);
    }

    public Task getTaskById(Long id) {
        return taskService.showById(id);
    }

    public List<Task> getAllTasks() {
        return taskService.showAll();
    }

    // User-related methods
    public Optional<User> findUserById(Long id) {
        return userService.findUserById(id);
    }

    public User createUser(User user) {
        return userService.createUser(user);
    }

    public List<User> getAllUsers() {
        return userService.showAllUsers();
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public void deleteAllUsers() {
        userService.deleteAll();
    }

    // Authentication-related methods
    public boolean login(String username, String password) {
        return authService.login(username, password);
    }

    public boolean authorize(String role) {
        return authService.authorize(role);
    }

    public void logout() {
        authService.logoutUser();
    }
}