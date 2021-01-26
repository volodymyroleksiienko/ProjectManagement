package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.jpa.SprintJPA;
import pl.edu.wsb.projectmanagement.service.SprintService;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintJPA sprintJPA;

    public SprintServiceImpl(SprintJPA sprintJPA) {
        this.sprintJPA = sprintJPA;
    }

    @Override
    public Sprint save(Sprint sprint) {
        return sprintJPA.save(sprint);
    }

    @Override
    public Sprint update(Sprint old) {
        return sprintJPA.save(old);
    }

    @Override
    public Sprint findById(int id) {
        return sprintJPA.findById(id).orElse(new Sprint());
    }

    @Override
    public List<Sprint> findAll() {
        return sprintJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        sprintJPA.deleteById(id);
    }
}
