package com.catagorymanagement.integration;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        Category category = new Category(1, "Furniture");
        categoryService.saveCategory(category);
    }

    @Test
    public void SaveCategoryIntegration() throws Exception {
        String payload = "{\"name\":\"Plastic\"}";

        mockMvc.perform(post("/category/registerCat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllCategories() throws Exception {

        mockMvc.perform(get("/category/allCategories"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getCategoriesBYID() throws Exception {

        mockMvc.perform(get("/category/cat/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateCategory() throws Exception {
        String updateCat = "{\"name\":\"Plastic\"}";

        mockMvc.perform(put("/category/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCat))
                .andExpect(status().isOk());

    }

    @Test
    void deleteCategory() throws Exception {

        mockMvc.perform(delete("/category/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
