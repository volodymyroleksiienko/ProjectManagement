package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;

import java.util.List;

public interface TaskJPA extends JpaRepository<Task,Integer> {
    List<Task> findByTaskStatusAndAndSprintId(TaskStatus status,int sprintId);
}
