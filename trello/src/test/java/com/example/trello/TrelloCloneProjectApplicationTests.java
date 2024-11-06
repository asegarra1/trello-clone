package com.example.trello;

import static org.junit.jupiter.api.Assertions.*;

import com.example.trello.repository.UserRepository;
import com.example.trello.service.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.trello.model.States;
import com.example.trello.repository.TaskRepository;
import com.example.trello.model.Task;
import com.example.trello.model.User;
import com.example.trello.service.TaskService;
import com.example.trello.service.UserService;

@SpringBootTest
class TrelloCloneProjectApplicationTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private Task testTask;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Test User");
        userRepository.save(testUser);
        testTask = new Task();
        testTask.setDescription("Sample Task");
        testTask.setState(States.TODO);

        testTask.setAssignedTo(testUser);
        taskRepository.save(testTask);
    }

    @Test
    @Transactional
    void testCreateTask() {
        // create a ne task
        Task task = taskService.createTask("New Task", testUser.getId(), null);

        assertNotNull(task, "Task should be created successfully");
        assertEquals("New Task", task.getDescription(), "Task description should match");
        assertEquals(States.TODO, task.getState(), "Initial task state should be TODO");
        assertEquals(testUser.getId(), task.getAssignedTo().getId(), "User should be assigned correctly");
    }

    @Test
    @Transactional
    void testStateTransition() {

        // change states
        Task updatedTask = taskService.updateState(testTask.getId());

        assertEquals(States.DOING, updatedTask.getState(), "Task state should transition from TODO to DOING");
    }

    @Test
    @Transactional
    void testAddComment() {
        // add a comment
        Task updatedTask = taskService.addComment(testTask.getId(), "This is a comment");

        assertEquals(1, updatedTask.getComments().size(), "Task should have one comment");
        assertEquals("This is a comment", updatedTask.getComments().get(0).getText(), "Comment text should match");
    }

    @Test
    @Transactional
    void testAddUser() {

        //add a new user to the task

        User anotherUser = new User();
        anotherUser.setName("Another User");
        userRepository.save(anotherUser);

        Task updatedTask = taskService.assignUser(testTask.getId(), anotherUser.getId());

        assertEquals(anotherUser.getId(), updatedTask.getAssignedTo().getId(), "The assigned user should match");
    }

    @Test
    @Transactional
    void testUserAuthentication() {

        //hypothetical user auth

        boolean loginResult = authService.login("user@example.com", "password");

        assertTrue(loginResult, "Login should succeed with valid credentials");

        boolean isAdmin = authService.authorize("ADMIN");
        assertTrue(isAdmin, "Authorization should succeed for ADMIN role");

        authService.logoutUser();
    }
}