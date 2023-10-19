package com.catagorymanagement.Repository;

import com.catagorymanagement.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
