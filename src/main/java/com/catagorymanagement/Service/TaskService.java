package com.catagorymanagement.Service;

import com.catagorymanagement.Entity.Task;
import com.catagorymanagement.Repository.TaskRepository;
import com.catagorymanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public List<Task> saveAllTask(List<Task> tasks) {
        return repository.saveAll(tasks);
    }

    public List<Task> getTasks() {
        List<Task> task = repository.findAll();
        return task;
    }

    public Task getTaskById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found this id:" + id));
    }

    public List<Task> getTaskPriority(String priority) {
        return repository.findByPriority(priority);
    }

    public Task updateTask(int id, Task task) {
        Task existingTask = repository.findById(id).get();

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
//         existingTask.setScheduleDate(task.getScheduleDate());
        existingTask.setCompleted(task.isCompleted());
        return repository.save(existingTask);
    }

    public String deleteTask(int id) {
        repository.deleteById(id);
        return "Task deleted";
    }

}
