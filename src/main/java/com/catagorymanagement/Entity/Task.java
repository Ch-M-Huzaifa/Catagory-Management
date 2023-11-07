package com.catagorymanagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "task_tbl")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private int id;
    @NotNull
    private String title;
    @NotNull(message = "Should not be empty")
    private String description;

    @NotNull(message = "It cannot be empty")
    private boolean completed;

    @NotNull
    private String priority;

    public Task(int id, String title, String description, boolean completed, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.priority = priority;
    }

    public Task() {

    }

}