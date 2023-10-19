package com.catagorymanagement.Entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="task_tbl")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private int id;
    private String title;
    private String description;
    private Date scheduleDate;

    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "cat_id",nullable = false)
    private Category category;

    public Task(int id, String title, String description, Date scheduleDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.scheduleDate = scheduleDate;
        this.completed = completed;

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

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
