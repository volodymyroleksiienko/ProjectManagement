package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wsb.projectmanagement.entity.Backlog;
import pl.edu.wsb.projectmanagement.jpa.BacklogJPA;
import pl.edu.wsb.projectmanagement.service.BacklogService;

import java.util.List;

public class BacklogServiceImpl implements BacklogService {
    private BacklogJPA backlogJPA;

    @Autowired
    public BacklogServiceImpl(BacklogJPA backlogJPA) {
        this.backlogJPA = backlogJPA;
    }

    @Override
    public void save(Backlog backlog) {
        backlogJPA.save(backlog);
    }

    @Override
    public Backlog findById(int id) {
        return backlogJPA.getOne(id);
    }

    @Override
    public List<Backlog> findAll() {
        return findAll();
    }

    @Override
    public void deleteByID(int id) {
        backlogJPA.deleteById(id);
    }
}
