package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.TaskItem;

public interface TaskItemJPA extends JpaRepository<TaskItem,Integer> {
}
