package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.TaskItem;
import pl.edu.wsb.projectmanagement.jpa.TaskItemJPA;
import pl.edu.wsb.projectmanagement.service.TaskItemService;

import java.util.List;

@Service
public class TaskItemServiceImpl implements TaskItemService {
    private TaskItemJPA taskItemJPA;

    public TaskItemServiceImpl(TaskItemJPA taskItemJPA) {
        this.taskItemJPA = taskItemJPA;
    }

    @Override
    public TaskItem save(TaskItem taskItem) {
        return taskItemJPA.save(taskItem);
    }

    @Override
    public TaskItem update(TaskItem old) {
        return taskItemJPA.save(old);
    }

    @Override
    public TaskItem findById(int id) {
        return taskItemJPA.findById(id).orElse(new TaskItem());
    }

    @Override
    public List<TaskItem> findAll() {
        return taskItemJPA.findAll();
    }

    @Override
    public void setStatus(int id, boolean status) {
        TaskItem taskItem = findById(id);
        if(taskItem!=null){
            taskItem.setStatus(status);
            save(taskItem);
        }
    }

    @Override
    public void deleteByID(int id) {
        taskItemJPA.deleteById(id);
    }
}
