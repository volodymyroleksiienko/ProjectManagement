package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.TaskItem;

import java.util.List;

public interface TaskItemService {
    TaskItem save(TaskItem taskItem);
    TaskItem update(TaskItem old);
    TaskItem findById(int id);
    List<TaskItem> findAll();
    void deleteByID(int id);
}
