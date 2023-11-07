package com.catagorymanagement.integration;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskService service;

    @BeforeEach
    public void setUp() {
        Task task = new Task(1, "Bikes", "About Bikes", true, "High");
        service.saveTask(task);
    }

    @Test
    public void TaskGetIntegration() throws Exception {

        mockMvc.perform(get("/task/allTasks"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TaskSaveIntegration() throws Exception {

        String payload = "{" +
                "\"title\"" +
                ":" +
                "\"Men's wearing\"" +
                "," +
                "\"description\"" +
                ":" +
                "\"All about men clothes\"" +
                "," +
                "\"completed\"" +
                ":" +
                "\"True\"" +
                "," +
                "\"priority\"" +
                ":" +
                "\"medium\"" +
                "}";

        mockMvc.perform(post("/task/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void TaskGetBYIDIntegration() throws Exception {

        mockMvc.perform(get("/task/taskById/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TaskUpdateIntegration() throws Exception {

        String updateTask = "{" +
                "\"title\"" +
                ":" +
                "\"Men's wearing\"" +
                "," +
                "\"description\"" +
                ":" +
                "\"All about men clothes\"" +
                "," +
                "\"completed\"" +
                ":" +
                "\"True\"" +
                "," +
                "\"priority\"" +
                ":" +
                "\"medium\"" +
                "}";


        mockMvc.perform(put("/task/update/1")
                        .content(updateTask)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TaskDeleteIntegration() throws Exception {

        mockMvc.perform(delete("/task/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

