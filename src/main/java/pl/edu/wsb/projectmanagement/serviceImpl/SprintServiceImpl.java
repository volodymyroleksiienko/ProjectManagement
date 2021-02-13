package pl.edu.wsb.projectmanagement.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.jpa.SprintJPA;
import pl.edu.wsb.projectmanagement.service.SprintService;
import pl.edu.wsb.projectmanagement.service.TaskService;

import java.util.List;

@Service
@AllArgsConstructor
public class SprintServiceImpl implements SprintService {
    private SprintJPA sprintJPA;
    private TaskService taskService;

    @Override
    public Sprint save(Sprint sprint) {
        return sprintJPA.save(sprint);
    }

    @Override
    public Sprint update(Sprint old) {
        if(old.getId()>0){
            Sprint sprint =  sprintJPA.findById(old.getId()).orElse(null);
            if (sprint!=null){
                sprint.setName(old.getName());
                sprint.setStartDate(old.getStartDate());
                sprint.setEndDate(old.getEndDate());
                return sprintJPA.save(old);
            }
        }
        return null;
    }

    @Override
    public Sprint countProgress(Sprint sprint) {
        int count = 0;
        System.out.println(sprint.getTaskList());
        for(Task task: sprint.getTaskList()){
            task = taskService.countProgress(task);
            if(task.getProgress()==100) count++;
        }
        if(count!=0) {
            int progress =(int) ( count / (double)sprint.getTaskList().size()*100);
            sprint.setProgress(progress);
            save(sprint);
        }
        return sprint;
    }

    @Override
    public Sprint findById(int id) {
        return sprintJPA.findById(id).orElse(new Sprint());
    }

    @Override
    public List<Sprint> findAll() {
        return sprintJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        sprintJPA.deleteById(id);
    }
}
