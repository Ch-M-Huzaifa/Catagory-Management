package com.catagorymanagement.unit;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Repository.CategoryRepository;
import com.catagorymanagement.Service.CategoryService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


public class CategoryServiceTest extends CatagoryManagementApplicationTests {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    private Category category = new Category(1, "Vehicles");
    private Category category2 = new Category(2, "Furniture");

    @Test
    public void registerCategory() {
        when(categoryRepository.save(category)).thenReturn(category);
        Category category1 = categoryService.saveCategory(category);
        System.out.println(category1);
        Assertions.assertEquals(category, category1);

    }

    @Test
    public void getCategories() {
        List<Category> cat1 = new ArrayList<>(List.of(Arrays.array(category)));

        when(categoryRepository.findAll()).thenReturn(cat1);

        List<Category> cat = categoryService.getCategories();
        System.out.println(cat);
        Assertions.assertEquals(cat1, cat);
    }

    @Test
    public void getCatById() {
        when(categoryRepository.findById(1)).thenReturn(Optional.ofNullable(category));

        Category catId = categoryService.getCatById(1);
        System.out.println(catId);

        Assertions.assertEquals(category, catId);
    }

    @Test
    public void deleteCatBYId() {
        when(categoryRepository.findById(1)).thenReturn(Optional.ofNullable(category));

        String cato = categoryService.deleteCategory(1);
        System.out.println(cato);
        Assertions.assertEquals("Category deleted", cato);
    }

}
