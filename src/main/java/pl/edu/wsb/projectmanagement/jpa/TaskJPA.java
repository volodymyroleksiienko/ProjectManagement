package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.Task;

public interface TaskJPA extends JpaRepository<Task,Integer> {
}
