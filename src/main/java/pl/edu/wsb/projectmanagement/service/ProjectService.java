package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    Project update(Project old);
    Project findById(int id);
    List<Project> findAll();
    void deleteByID(int id);
}
