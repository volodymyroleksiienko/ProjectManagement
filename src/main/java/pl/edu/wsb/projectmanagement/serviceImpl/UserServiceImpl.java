package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.jpa.UserJPA;
import pl.edu.wsb.projectmanagement.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserJPA userJPA;

    public UserServiceImpl(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    @Override
    public User save(User user) {
        return userJPA.save(user);
    }

    @Override
    public User update(User old) {
        return userJPA.save(old);
    }

    @Override
    public User findById(int id) {
        return userJPA.findById(id).orElse(new User());
    }

    @Override
    public List<User> findAll() {
        return userJPA.findAll();
    }

    @Override
    public void deleteByID(int id) {
        userJPA.deleteById(id);
    }
}
