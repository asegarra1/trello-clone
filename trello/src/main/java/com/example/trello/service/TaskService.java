package com.example.trello.service;

import com.example.trello.model.Task;
import com.example.trello.model.User;
import com.example.trello.model.Comment;
import com.example.trello.repository.TaskRepository;
import com.example.trello.repository.UserRepository;

import jakarta.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.trello.model.States;

import java.util.List;


/*
Business logic for task-related operations like creating a task, assigning a user, updating state, and adding comments.
Main application logic for tasks
 */


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Task createTask(String description, Long userId, List<Comment> comments) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Task task = new Task();
        task.setDescription(description);
        task.setState(States.TODO);
        task.setAssignedTo(user);

        if (comments != null) {
            for (Comment comment : comments) {
                comment.setTask(task);
                task.getComments().add(comment);
            }
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    
    }
    @Transactional
    public void deleteAll() {
        taskRepository.deleteAll();

    }

    @Transactional
    public Task updateState(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

        States next = task.getState().next();

        task.setState(next);
        return taskRepository.save(task);
    }

    @Transactional
    public Task assignUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        task.setAssignedTo(user);
        return taskRepository.save(task);
    }

    @Transactional
    public void removeUsers(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        task.removeAssignTo(task);
        taskRepository.save(task);

    }

    @Transactional
    public Task addComment(Long taskId, String commentText) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setTask(task);

        task.getComments().add(comment);
        return taskRepository.save(task);
    }

    @Transactional
    public Task removeComments(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        task.removeComments();
        return taskRepository.save(task);

        
    }

    @Transactional
    public Task showById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        return task;

    }


    @Transactional
    public List<Task> showAll() {
        return taskRepository.findAll();
    }
}