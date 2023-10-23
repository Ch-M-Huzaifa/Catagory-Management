package com.catagorymanagement.Controller;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Service.TaskService;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/register")
    public Task registerTask(@RequestBody Task task){
        return service.saveTask(task);
    }
    @PostMapping("/registers")
    public List<Task> registerTasks(@RequestBody List<Task> tasks){
        return service.saveAllTask(tasks);
    }

    @GetMapping("/allTasks")
    public List<Task> getAllTasks(){
        return service.getTasks();
    }

    @GetMapping("/taskById/{id}")
    public Task getTaskBYId(@PathVariable("id") int id){
        return service.getTaskById(id);
    }
    @GetMapping("/taskByDate/{scheduleDate}")
    public List<Task> getTaskByD(@PathVariable("scheduleDate") LocalDate scheduleDate){
        return service.getTaskByDate(scheduleDate);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.deleteTask(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable int id,@RequestBody Task task){
        return service.updateTask(id,task);
    }

    @PatchMapping("/updateD/{id}")
    public Task updateTaskByDate(@PathVariable int id,@RequestBody Task scheduleDate){
        return service.updateTaskDate(id,scheduleDate);
    }


}
