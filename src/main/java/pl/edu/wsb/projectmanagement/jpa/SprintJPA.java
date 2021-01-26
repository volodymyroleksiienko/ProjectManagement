package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.Sprint;

public interface SprintJPA extends JpaRepository<Sprint,Integer> {
}
