package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Sprint;

import java.util.List;

public interface SprintService {
    Sprint save(Sprint sprint);
    Sprint update(Sprint old);
    Sprint countProgress(Sprint sprint);
    Sprint findById(int id);
    List<Sprint> findAll();
    void deleteByID(int id);
}
