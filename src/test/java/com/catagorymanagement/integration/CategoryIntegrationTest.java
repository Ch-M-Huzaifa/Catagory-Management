package com.catagorymanagement.integration;

import com.catagorymanagement.Entity.Category;
import com.catagorymanagement.Service.CategoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void SaveCategoryIntegration() throws Exception {
        String payload = "{\"name\":\"Plastic\"}";
       MvcResult result= mockMvc.perform(post("/category/registerCat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andReturn();
       String responseContent =result.getResponse().getContentAsString();
        Category category=objectMapper.readValue(responseContent, Category.class);
        Assertions.assertEquals("Plastic",category.getName());
        categoryService.deleteCategory(category.getId());
    }

    @Test
    void getAllCategories() throws Exception {
        Category category1=new Category();
        category1.setName("Cars");
        categoryService.saveCategory(category1);

        Category category2=new Category();
        category2.setName("Bikes");
        categoryService.saveCategory(category2);

        List<Category> categories=new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        MvcResult result=mockMvc.perform(get("/category/allCategories"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent=result.getResponse().getContentAsString();
        List<Category> categoryList=objectMapper.readValue(responseContent, new TypeReference<List<Category>>(){});
        Assertions.assertEquals(category1.getName(),categoryList.get(0).getName());
        Assertions.assertEquals(category2.getName(),categoryList.get(1).getName());

        for (Category category:categoryList){
            categoryService.deleteCategory(category.getId());
        }
    }

    @Test
    void getCategoriesBYID() throws Exception {
    Category category=new Category();
    category.setName("Vehicle");
    categoryService.saveCategory(category);

    MvcResult result= mockMvc.perform(get("/category/cat/"+category.getId()))
                .andExpect(status().isOk())
                .andReturn();
       String reponseContent=result.getResponse().getContentAsString();
       Category category1=objectMapper.readValue(reponseContent, Category.class);
       Assertions.assertEquals(category.getName(),category1.getName());
    categoryService.deleteCategory(category1.getId());
    }

    @Test
    void updateCategory() throws Exception {
        Category category1=new Category();
        category1.setName("Vehicle");
        categoryService.saveCategory(category1);

        String updateCat = "{\"name\":\"Plastic\"}";

        MvcResult result= mockMvc.perform(put("/category/update/"+category1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCat))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent =result.getResponse().getContentAsString();
        Category category=objectMapper.readValue(responseContent, Category.class);
        Assertions.assertEquals("Plastic",category.getName());
        categoryService.deleteCategory(category.getId());
    }

    @Test
    void deleteCategory() throws Exception {
        Category category=new Category();
        category.setName("Furniture");
        categoryService.saveCategory(category);

        mockMvc.perform(delete("/category/delete/"+category.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }
}
