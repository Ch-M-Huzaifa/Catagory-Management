package com.catagorymanagement.Controller;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/task{id}")
    public Task getTaskBYId(@PathVariable int id){
        return service.getTaskById(id);
    }
  @GetMapping("/task/{date}")
    public Task getTaskByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return service.getTaskByDate(date);
    }
    @DeleteMapping("delete{id}")
    public String delete(@PathVariable int id){
        return service.deleteTask(id);
    }

    @PostMapping("/update")
    public Task updateTask(@RequestBody Task id){
        return service.updateTask(id);
    }


}
