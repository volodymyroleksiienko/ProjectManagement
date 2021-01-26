package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Backlog;
import pl.edu.wsb.projectmanagement.jpa.BacklogJPA;
import pl.edu.wsb.projectmanagement.service.BacklogService;

import java.util.List;

@Service
public class BacklogServiceImpl implements BacklogService {
    private BacklogJPA backlogJPA;

    @Autowired
    public BacklogServiceImpl(BacklogJPA backlogJPA) {
        this.backlogJPA = backlogJPA;
    }

    @Override
    public Backlog save(Backlog backlog) {
        return backlogJPA.save(backlog);
    }

    @Override
    public Backlog update(Backlog old) {
        if(old.getId()>0){
            Backlog backlog = backlogJPA.findById(old.getId()).orElse(null);
            if(backlog!=null){
                backlog.setDescription(old.getDescription());
                backlog.setPriority(old.getPriority());
                return backlogJPA.save(backlog);
            }
        }
        return null;
    }

    @Override
    public Backlog findById(int id) {
        return backlogJPA.findById(id).orElse(new Backlog());
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
