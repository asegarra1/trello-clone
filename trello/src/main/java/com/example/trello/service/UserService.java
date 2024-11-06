package com.example.trello.service;

import com.example.trello.model.User;
import com.example.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Business logic related to User operations, similar to how TaskService does for tasks
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(Long id) {

        return userRepository.findById(id);
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long Id) {

        userRepository.deleteById(Id);
    }

    public void deleteAll() {
        userRepository.deleteAll();}

}