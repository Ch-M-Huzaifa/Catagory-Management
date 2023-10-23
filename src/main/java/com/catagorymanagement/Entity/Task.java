package com.catagorymanagement.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name="task_tbl")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private int id;
    @NotNull
    private String title;
    @NotNull(message = "Should not be empty")
    private String description;

    @NotNull(message = "Date shouldn't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

    @NotNull(message = "It cannot be empty")
    private boolean completed;

    @NotNull
    private String priority;

    public Task(int id, String title, String description,LocalDate scheduleDate,boolean completed,String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.scheduleDate = scheduleDate;
        this.completed = completed;
        this.priority=priority;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


}
