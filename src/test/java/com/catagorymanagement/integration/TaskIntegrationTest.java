package com.catagorymanagement.integration;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Service.TaskService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TaskGetIntegration() throws Exception {
        Task task1= new Task();
        task1.setTitle("Bikes");
        task1.setDescription("About Bikes");
        task1.setCompleted(true);
        task1.setPriority("High");
        service.saveTask(task1);

        Task task2= new Task();
        task2.setTitle("Cars");
        task2.setDescription("About Cars");
        task2.setCompleted(false);
        task2.setPriority("low");
        service.saveTask(task2);

        List<Task> tasks=new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        MvcResult result=mockMvc.perform(get("/task/allTasks"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent= result.getResponse().getContentAsString();
        List<Task> taskList=objectMapper.readValue(responseContent, new TypeReference<List<Task>>() {});
        Assertions.assertEquals(task1.getTitle(),taskList.get(0).getTitle());
        Assertions.assertEquals(task2.getTitle(),taskList.get(1).getTitle());

        for (Task task:taskList){
            service.deleteTask(task.getId());
        }
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

        MvcResult result=mockMvc.perform(post("/task/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andReturn();
        String responnceContent= result.getResponse().getContentAsString();
        Task task=objectMapper.readValue(responnceContent,Task.class);
        Assertions.assertEquals("Men's wearing",task.getTitle());
        service.deleteTask(task.getId());
    }

    @Test
    public void TaskGetBYIDIntegration() throws Exception {
        Task task1= new Task();
        task1.setTitle("Bikes");
        task1.setDescription("About Bikes");
        task1.setCompleted(true);
        task1.setPriority("High");
        service.saveTask(task1);

        MvcResult result= mockMvc.perform(get("/task/taskById/"+task1.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String reponseContent=result.getResponse().getContentAsString();
        Task task=objectMapper.readValue(reponseContent, Task.class);
        Assertions.assertEquals(task1.getTitle(),task.getTitle());
        Assertions.assertEquals(task1.isCompleted(),task.isCompleted());
        service.deleteTask(task.getId());
    }

    @Test
    public void TaskUpdateIntegration() throws Exception {
        Task task1= new Task();
        task1.setTitle("Bikes");
        task1.setDescription("About Bikes");
        task1.setCompleted(true);
        task1.setPriority("High");
        service.saveTask(task1);

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

        MvcResult result= mockMvc.perform(put("/task/update/"+task1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateTask))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent =result.getResponse().getContentAsString();
        Task task=objectMapper.readValue(responseContent, Task.class);
        Assertions.assertEquals("Men's wearing",task.getTitle());
        service.deleteTask(task.getId());
    }

    @Test
    public void TaskDeleteIntegration() throws Exception {
        Task task1= new Task();
        task1.setTitle("Bikes");
        task1.setDescription("About Bikes");
        task1.setCompleted(true);
        task1.setPriority("High");
        service.saveTask(task1);

        mockMvc.perform(delete("/task/delete/"+task1.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

