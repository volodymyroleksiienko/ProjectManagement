package pl.edu.wsb.projectmanagement.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.service.ProjectService;
import pl.edu.wsb.projectmanagement.service.SprintService;
import pl.edu.wsb.projectmanagement.service.TaskService;
import pl.edu.wsb.projectmanagement.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/sprint")
@AllArgsConstructor
public class SprintController {

    private SprintService sprintService;
    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;



    @GetMapping("/info/{id}")
    public String getSprintInfo(@PathVariable int id, Model model, Principal principal){
        User user = userService.getByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("sprint",sprintService.findById(id));
        model.addAttribute("openTasks",taskService.findByTaskStatusAndAndSprintId(TaskStatus.OPEN,id));
        model.addAttribute("inProcessTasks",taskService.findByTaskStatusAndAndSprintId(TaskStatus.IN_PROCESS,id));
        model.addAttribute("doneTasks",taskService.findByTaskStatusAndAndSprintId(TaskStatus.DONE,id));
        return "tasks";
    }

    @PostMapping("/create/{projectId}")
    public String createBacklog(@PathVariable int projectId, Sprint sprint){
        sprint.setProject(projectService.findById(projectId));
        sprintService.save(sprint);
        return "redirect:/projects/info/"+projectId;
    }

    @PostMapping("/update/{projectId}")
    public String updateBacklog(@PathVariable int projectId, Sprint sprint){
        sprint.setProject(projectService.findById(projectId));
        sprintService.update(sprint);
        return "redirect:/projects/info/"+projectId;
    }

    @GetMapping("/delete/{projectId}")
    public String deleteBacklog(@PathVariable int projectId, int id){
        sprintService.deleteByID(id);
        return "redirect:/projects/info/"+projectId;
    }
}
