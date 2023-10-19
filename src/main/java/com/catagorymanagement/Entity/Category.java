package com.catagorymanagement.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_tbl")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "cat_id")
    private int id;
    private String name;


    public Category(int id, String name) {
        this.id = id;
        this.name = name;

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

}
