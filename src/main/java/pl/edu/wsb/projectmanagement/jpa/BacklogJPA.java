package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.Backlog;

public interface BacklogJPA extends JpaRepository<Backlog,Integer> {

}
