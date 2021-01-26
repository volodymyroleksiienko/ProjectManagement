package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    Task update(Task old);
    Task findById(int id);
    List<Task> findAll();
    void deleteByID(int id);
}
