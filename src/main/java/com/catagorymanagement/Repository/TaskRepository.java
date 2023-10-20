package com.catagorymanagement.Repository;

import com.catagorymanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    Task findByScheduleDate(Date scheduleDate);

}
