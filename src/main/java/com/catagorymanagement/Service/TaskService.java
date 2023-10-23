package com.catagorymanagement.Service;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Repository.TaskRepository;
import com.catagorymanagement.exception.ResourceNotFoundException;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task){
          LocalDate currentDate= LocalDate.now();
        if (task.getScheduleDate().isAfter(currentDate)) {

        return repository.save(task);
    }
        else {
            return null;
        }
    } 
    public List<Task> saveAllTask(List<Task> tasks){
        return repository.saveAll(tasks);
    }
    public List<Task> getTasks(){
        return repository.findAll();
    }
    public Task getTaskById(int id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found this id:"+ id));
    }

  public List<Task> getTaskByDate(LocalDate scheduleDate) {
        return repository.findByScheduleDate(scheduleDate);
    }
    public String deleteTask(int id){
        repository.deleteById(id);
        return "Task deleted";
    }

    public Task updateTask(int id ,Task task) {
        Task existingTask=repository.findById(id).get();

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
         existingTask.setScheduleDate(task.getScheduleDate());
        existingTask.setCompleted(task.isCompleted());
        return repository.save(existingTask);
    }
    public Task updateTaskDate(int id,Task scheduleDate) {
        Task existingTask = repository.findById(id).get();

        LocalDate currentDate = LocalDate.now();

        if (scheduleDate.getScheduleDate().isAfter(currentDate)) {
            existingTask.setScheduleDate(scheduleDate.getScheduleDate());
            return repository.save(existingTask);
        }
        return null;
    }
}
