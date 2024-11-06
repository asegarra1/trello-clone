package com.example.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/*
 * Represents comments on a task, containing id, text, and a link to the associated Task. 
 * Used to allow users to add comments on tasks.
 */

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}