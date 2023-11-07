package com.catagorymanagement.Controller;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/registerCat")
    public Category registerCategory(@Valid @RequestBody Category category) {
        return service.saveCategory(category);
    }

    @PostMapping("/registerCats")
    public List<Category> registerCategories(@RequestBody List<Category> categories) {
        return service.saveCategories(categories);
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories() {
        return service.getCategories();
    }

    @GetMapping("/cat/{id}")
    public Category getCatBYId(@PathVariable("id") int id) {
        return service.getCatById(id);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        return service.updateCategory(id, category);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return service.deleteCategory(id);
    }

}
