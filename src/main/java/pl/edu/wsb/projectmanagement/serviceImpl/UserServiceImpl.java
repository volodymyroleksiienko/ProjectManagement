package pl.edu.wsb.projectmanagement.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
        if(old.getId()>0) {
            User user = userJPA.findById(old.getId()).orElse(null);
            if(user!=null){
                user.setFirstName(old.getFirstName());
                user.setLastName(old.getLastName());
                user.setPosition(old.getPosition());
                user.setPhoneNumber(old.getPhoneNumber());
                return userJPA.save(user);
            }
        }
        return null;
    }

    @Override
    public User update(MultipartFile multipartFile, User old) {
        if(old.getId()>0) {
            User user = userJPA.findById(old.getId()).orElse(null);
            if(user!=null && multipartFile != null){
                user.setFirstName(old.getFirstName());
                user.setLastName(old.getLastName());
                user.setPosition(old.getPosition());
                user.setPhoneNumber(old.getPhoneNumber());
                if (!multipartFile.isEmpty()) {
                    try {
                        user.setPicture(multipartFile.getBytes());
                        user.setPictureName(multipartFile.getOriginalFilename());
                        user.setPictureType(multipartFile.getContentType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return userJPA.save(user);
            }
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userJPA.findByUsername(username);
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
