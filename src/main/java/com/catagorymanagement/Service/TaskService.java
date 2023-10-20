package com.catagorymanagement.Service;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task){
        return repository.save(task);
    }
    public List<Task> saveAllTask(List<Task> tasks){
        return repository.saveAll(tasks);
    }
    public List<Task> getTasks(){
        return repository.findAll();
    }
    public Task getTaskById(int id){
        return repository.findById(id).orElse(null);
    }

  public Task getTaskByDate(Date scheduleDate) {
        return repository.findByScheduleDate(scheduleDate);
    }
    public String deleteTask(int id){
        repository.deleteById(id);
        return "Task deleted";
    }

    public Task updateTask(Task task) {
        Task existingTask=repository.findById(task.getId()).orElse(null);

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
         existingTask.setScheduleDate(task.getScheduleDate());
        existingTask.setCompleted(task.isCompleted());
        return repository.save(existingTask);


    }

}
