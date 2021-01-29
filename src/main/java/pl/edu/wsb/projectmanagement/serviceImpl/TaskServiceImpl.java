package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.TaskItem;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;
import pl.edu.wsb.projectmanagement.jpa.TaskJPA;
import pl.edu.wsb.projectmanagement.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskJPA taskJPA;

    public TaskServiceImpl(TaskJPA taskJPA) {
        this.taskJPA = taskJPA;
    }

    @Override
    public Task save(Task task) {
        return taskJPA.save(task);
    }

    @Override
    public Task save(Task task, String[] subTasks) {
        List<TaskItem> taskItems = new ArrayList<>();
        for (String item:subTasks) {
            TaskItem taskItem = new TaskItem(item,false);
            taskItem.setTask(task);
            taskItems.add(taskItem);
        }
        task.setItemList(taskItems);
        return save(task);
    }

    @Override
    public Task update(Task old) {
        return taskJPA.save(old);
    }

    @Override
    public Task findById(int id) {
        return taskJPA.findById(id).orElse(new Task());
    }

    @Override
    public List<Task> findByTaskStatus(TaskStatus status) {
        return taskJPA.findByTaskStatus(status);
    }

    @Override
    public List<Task> findAll() {
        return taskJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        taskJPA.deleteById(id);
    }
}
