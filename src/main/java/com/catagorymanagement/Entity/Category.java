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

    @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_fk", referencedColumnName = "id")
    private List<Task> task;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
