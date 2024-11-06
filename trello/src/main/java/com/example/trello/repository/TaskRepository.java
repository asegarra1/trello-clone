package com.example.trello.repository;

import com.example.trello.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Repository interface for Task, extending JpaRepository to handle basic CRUD operations with the database
 */

 
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}