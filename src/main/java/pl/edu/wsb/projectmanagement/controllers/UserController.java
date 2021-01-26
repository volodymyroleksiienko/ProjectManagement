package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.service.UserService;

import java.security.Principal;

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

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal){
        model.addAttribute("user",userService.getByUsername(principal.getName()));
        return "user_profile";
    }

    @GetMapping("/editUserProfile")
    public String editUserProfile(Model model, Principal principal){
        model.addAttribute("user",userService.getByUsername(principal.getName()));
        return "edit_user_profile";
    }

    @PostMapping("/editUserProfile")
    public String editUserProfile(@RequestParam MultipartFile multipartFile,User user){
        userService.update(multipartFile,user);
        return "redirect:/user/profile";
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
        if(doc.getPicture()==null){
            return null;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getPictureType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getPictureName()+"\"")
                .body(new ByteArrayResource(doc.getPicture()));
    }
}

