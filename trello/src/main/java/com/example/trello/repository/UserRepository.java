package com.example.trello.repository;

import com.example.trello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
Repository interface for Users, extending JpaRepository to handle basic CRUD operations with the database
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}