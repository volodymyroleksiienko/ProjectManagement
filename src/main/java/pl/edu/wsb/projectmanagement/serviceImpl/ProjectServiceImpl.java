package pl.edu.wsb.projectmanagement.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Project;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.jpa.ProjectJPA;
import pl.edu.wsb.projectmanagement.service.ProjectService;
import pl.edu.wsb.projectmanagement.service.SprintService;

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
        int count = 0;
        for(Sprint sprint: project.getSprintsList()){
            sprint = sprintService.countProgress(sprint);
            if(sprint.getProgress()==100) count++;
        }
        project.setProgress(count/project.getSprintsList().size()*100);
        save(project);
        System.out.println(project.getProgress());
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
    public void deleteByID(int id) {
        projectJPA.deleteById(id);
    }
}
