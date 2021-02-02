package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Project;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.service.ProjectService;
import pl.edu.wsb.projectmanagement.service.UserService;

@Controller
@RequestMapping("/team")
public class TeamController {
    private ProjectService projectService;
    private UserService userService;
    private JavaMailSender javaMailSender;

    public TeamController(ProjectService projectService, UserService userService, JavaMailSender javaMailSender) {
        this.projectService = projectService;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/{id}")
    public String getTeamOfProject(@PathVariable int id,boolean fail,boolean success, Model model){
        model.addAttribute("project",projectService.findById(id));
        model.addAttribute("success",success);
        model.addAttribute("fail",fail);
        return "team";
    }

    @PostMapping("/add/{id}")
    public String addTeamOfProject(@PathVariable int id,String email){
        Project project = projectService.findById(id);
        User user = userService.getByUsername(email);
        if(user==null){
            sendEmailRequest(email);
            return "redirect:/team/"+id+"?fail=true";
        }else{
            project.getTeamList().add(user);
            user.getCollaborators().add(project);
            userService.save(user);
            projectService.save(project);
            return "redirect:/team/"+id+"?success=true";
        }
    }

    public void sendEmailRequest(String email){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Test message");
        msg.setText("Test message");

        javaMailSender.send(msg);
    }


}
