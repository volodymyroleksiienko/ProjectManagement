package pl.edu.wsb.projectmanagement.service;

import org.springframework.ui.Model;
import pl.edu.wsb.projectmanagement.entity.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    Project update(Project old);
    Project countProgress(Project old);
    Project findById(int id);
    void setModelTaskStatistic(Project project, Model model);
    List<Project> findAll();
    void deleteByID(int id);
}
