package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Backlog;

import java.util.List;

public interface BacklogService {
    void save(Backlog backlog);
    Backlog findById(int id);
    List<Backlog> findAll();
    void deleteByID(int id);
}
