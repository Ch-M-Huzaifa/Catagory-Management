package com.catagorymanagement.Service;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Repository.CategoryRepository;
import com.catagorymanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> saveCategories(List<Category> categories) {
        return repository.saveAll(categories);
    }

    public List<Category> getCategories() {
        return repository.findAll();
    }

    public Category getCatById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found this id:" + id));
    }

    public Category updateCategory(int id, Category category) {
        Category existingCategory = repository.findById(id).get();
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    public String deleteCategory(int id) {
        repository.deleteById(id);
        return "Category deleted";
    }
}
