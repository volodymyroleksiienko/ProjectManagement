package pl.edu.wsb.projectmanagement.service;

import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(User old);
    User findById(int id);
    List<User> findAll();
    void deleteByID(int id);
}
