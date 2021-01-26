package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Backlog;

import java.util.List;

public interface BacklogService {
    Backlog save(Backlog backlog);
    Backlog update(Backlog old);
    Backlog findById(int id);
    List<Backlog> findAll();
    void deleteByID(int id);
}
