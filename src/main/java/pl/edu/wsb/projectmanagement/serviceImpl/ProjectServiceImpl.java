package pl.edu.wsb.projectmanagement.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.edu.wsb.projectmanagement.entity.Project;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.jpa.ProjectJPA;
import pl.edu.wsb.projectmanagement.service.ProjectService;
import pl.edu.wsb.projectmanagement.service.SprintService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectJPA  projectJPA;
    private SprintService sprintService;

    @Override
    public Project save(Project project) {
        return projectJPA.save(project);
    }

    @Override
    public Project update(Project old) {
        if(old.getId()>0){
            Project project = projectJPA.findById(old.getId()).orElse(null);
            if(project!=null){
                project.setName(old.getName());
                project.setDescription(old.getDescription());
                project.setStarted(old.getStarted());
                project.setDeadline(old.getDeadline());
                return projectJPA.save(project);
            }
        }
        return null;
    }

    @Override
    public Project countProgress(Project project) {
        if(project== null) return null;
        int count = 0;
        for(Sprint sprint: project.getSprintsList()){
            sprint = sprintService.countProgress(sprint);
            if(sprint.getProgress()==100) count++;
        }
        if(count!=0) {
            project.setProgress((int) ( count / (double) project.getSprintsList().size() * 100));
            save(project);
        }
        return project;
    }

    @Override
    public Project findById(int id) {
        return projectJPA.findById(id).orElse(new Project());
    }

    @Override
    public List<Project> findAll() {
        return projectJPA.findAll();
    }

    @Override
    public void setModelTaskStatistic(Project project,Model model){
        int open=0;
        int inProcess=0;
        int done=0;
        List<Task> allTasks = new ArrayList<>();
        for(Sprint sprint:project.getSprintsList()){
            allTasks.addAll(sprint.getTaskList());
            for(Task task:sprint.getTaskList()){
                switch (task.getTaskStatus()){
                    case OPEN:open++; break;
                    case IN_PROCESS:inProcess++; break;
                    case DONE:done++; break;
                }
            }
        }
        open = (int) ( open/ (float) allTasks.size()*100);
        inProcess = (int) ( inProcess/ (float) allTasks.size()*100);
        done = (int) ( done/ (float) allTasks.size()*100);
        model.addAttribute("openProgress",open);
        model.addAttribute("inProcessProgress",inProcess);
        model.addAttribute("doneProgress",done);
    }

    @Override
    public void deleteByID(int id) {
        projectJPA.deleteById(id);
    }
}
