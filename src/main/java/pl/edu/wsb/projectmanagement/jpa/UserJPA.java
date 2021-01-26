package pl.edu.wsb.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wsb.projectmanagement.entity.User;

public interface UserJPA extends JpaRepository<User, Integer> {
    User findByUsername (String username);
}
