package com.catagorymanagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category_tbl")
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Name should not be null")
    private String name;

    @OneToMany(targetEntity = Task.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "task_fk", referencedColumnName = "id")
    private List<Task> task;



    public Category(int id, String name,List<Task> task) {
        this.id = id;
        this.name = name;
        this.task=task;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }


}
