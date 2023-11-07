package com.catagorymanagement.unit;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Repository.TaskRepository;
import com.catagorymanagement.Service.TaskService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TaskServiceTest extends CatagoryManagementApplicationTests {
    Task task = new Task(1, "Bikes", "About Bikes", true, "High");
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void saveTask() {
        when(taskRepository.save(task)).thenReturn(task);
        Task task1 = taskService.saveTask(task);
        System.out.println(task1);
        Assertions.assertEquals(task, task1);
    }

    @Test
    public void getTask() {
        List<Task> task1 = new ArrayList<>(List.of(Arrays.array(task)));
        when(taskRepository.findAll()).thenReturn(task1);
        List<Task> tasks = taskService.getTasks();
        System.out.println(tasks);
        Assertions.assertEquals(task1, tasks);
    }

    @Test
    public void getTaskBYId() {
        when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(task));
        Task task1 = taskService.getTaskById(1);
        System.out.println(task1);
        Assertions.assertEquals(task, task1);
    }

    @Test
    public void getTaskByPriority() {
        List<Task> task1 = new ArrayList<>(List.of(Arrays.array(task)));
        when(taskRepository.findByPriority("High")).thenReturn(task1);
        List<Task> taskP = taskService.getTaskPriority("High");
        System.out.println(taskP);
        Assertions.assertEquals(task1, taskP);
    }

    @Test
    public void deleteTask() {
        when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(task));
        String deleteTask = taskService.deleteTask(1);
        System.out.println(deleteTask);

        Assertions.assertEquals("Task deleted", deleteTask);
    }

}





