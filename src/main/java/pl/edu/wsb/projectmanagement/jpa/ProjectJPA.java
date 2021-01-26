package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wsb.projectmanagement.entity.Project;

import java.util.List;

public interface ProjectJPA extends JpaRepository<Project,Integer> {
}
