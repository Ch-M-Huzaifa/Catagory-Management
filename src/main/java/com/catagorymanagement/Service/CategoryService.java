package com.catagorymanagement.Service;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category saveCategory(Category category){
        return repository.save(category);
    }
    public List<Category> saveCategories(List<Category> categories){
        return repository.saveAll(categories);
    }
    public List<Category> getCategories(){
        return repository.findAll();
    }
    public Category getCatById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteCategory(int id){
        repository.deleteById(id);
        return "Category deleted";
    }


    public Category updateCategory(Category category) {
        Category existingCategory=repository.findById(category.getId()).orElse(null);

        existingCategory.setName(category.getName());
        return repository.save(existingCategory);


    }
}
