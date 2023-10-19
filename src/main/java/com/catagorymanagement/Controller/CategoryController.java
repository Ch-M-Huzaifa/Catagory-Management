package com.catagorymanagement.Controller;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/registerCat")
    public Category registerCategory(@RequestBody Category category){
        return service.saveCategory(category);
    }
    @PostMapping("/registerCats")
    public List<Category> registerCategories(@RequestBody List<Category> categories){
        return service.saveCategories(categories);
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories(){
        return service.getCategories();
    }

    @GetMapping("/task{id}")
    public Category getCatBYId(@PathVariable int id){
        return service.getCatById(id);
    }

    @DeleteMapping("delete{id}")
    public String delete(@PathVariable int id){
        return service.deleteCategory(id);
    }

    @PostMapping("/update")
    public Category updateCategory(@RequestBody Category id){
        return service.updateCategory(id);
    }
}
