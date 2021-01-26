package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model model){
        return "";
    }

    @PostMapping("/create")
    public String createUser(User user){
        userService.save(user);
        return "";
    }

    @PostMapping("/update")
    public String updateUser(User user){
        userService.update(user);
        return "";
    }

    @GetMapping("/delete")
    public String deleteUser(int id){
        userService.deleteByID(id);
        return "redirect:/logout";
    }


    @GetMapping("/getUserImg/{userId}")
    public ResponseEntity<ByteArrayResource> getImgByProductId(@PathVariable int userId){
        User doc = userService.findById(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getPictureType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getPictureName()+"\"")
                .body(new ByteArrayResource(doc.getPicture()));
    }
}

