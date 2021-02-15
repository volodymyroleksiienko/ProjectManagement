package pl.edu.wsb.projectmanagement.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.TaskItem;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.jpa.TaskJPA;
import pl.edu.wsb.projectmanagement.service.TaskService;
import pl.edu.wsb.projectmanagement.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskJPA taskJPA;
    private UserService userService;


    @Override
    public Task save(Task task) {
        if (task.getAssignee()!=null && userService.findById(task.getAssignee().getId())!=null) {
            task.setTaskStatus(TaskStatus.IN_PROCESS);
        }else{
            task.setAssignee(null);
            task.setTaskStatus(TaskStatus.OPEN);
        }
        return taskJPA.save(task);
    }

    @Override
    public Task save(Task task, String[] subTasks) {
        List<TaskItem> taskItems = new ArrayList<>();
        if (subTasks!=null) {
            for (String item : subTasks) {
                TaskItem taskItem = new TaskItem(item, false);
                taskItem.setTask(task);
                taskItems.add(taskItem);
            }
        }
        task.setItemList(taskItems);
        return save(task);
    }

    @Override
    public Task update(Task old) {
        Task taskDB = findById(old.getId());
        taskDB.setName(old.getName());
        if(old.getAssignee().getId()>0) {
            taskDB.setAssignee(userService.findById((old.getAssignee().getId())));
        }taskDB.getSprint().setId(old.getSprint().getId());
        taskDB.setTaskStatus(old.getTaskStatus());
        taskDB.setProgress(old.getProgress());
        return save(taskDB);
    }

    @Override
    public Task countProgress(Task task) {
        int count  =0;
        for(TaskItem taskItem :task.getItemList()){
            if(taskItem.isStatus()) count++;
        }
        if(count>0) {
            task.setProgress(count / task.getItemList().size() * 100);
            save(task);
        }
        return task;
    }

    @Override
    public Task findById(int id) {
        return taskJPA.findById(id).orElse(new Task());
    }

    @Override
    public List<Task> findByTaskStatusAndAndSprintId(TaskStatus status, int sprintId) {
        return taskJPA.findByTaskStatusAndAndSprintId(status,sprintId);
    }



    @Override
    public List<Task> findAll() {
        return taskJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        taskJPA.deleteById(id);
    }

    @Override
    public void deleteAssigneeByID(int id) {
        Task task= findById(id);
        task.setAssignee(null);
        save(task);
    }
}
