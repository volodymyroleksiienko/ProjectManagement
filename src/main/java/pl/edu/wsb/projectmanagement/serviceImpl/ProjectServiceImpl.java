package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Project;
import pl.edu.wsb.projectmanagement.jpa.ProjectJPA;
import pl.edu.wsb.projectmanagement.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectJPA  projectJPA;

    public ProjectServiceImpl(ProjectJPA projectJPA) {
        this.projectJPA = projectJPA;
    }

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
                return projectJPA.save(old);
            }
        }
        return null;
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
