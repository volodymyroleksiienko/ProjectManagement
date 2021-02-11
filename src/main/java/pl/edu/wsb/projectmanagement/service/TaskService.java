package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    Task save(Task task,String[] subTasks);
    Task update(Task old);
    Task findById(int id);
    List<Task> findByTaskStatusAndAndSprintId(TaskStatus status,int sprintId);
    List<Task> findAll();
    void deleteByID(int id);
}
