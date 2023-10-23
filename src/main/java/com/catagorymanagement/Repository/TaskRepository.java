package com.catagorymanagement.Repository;

import com.catagorymanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findByScheduleDate(LocalDate scheduleDate);

      List<Task> findByPriority(String priority);
}
