package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.entity.User;
import pl.edu.wsb.projectmanagement.service.SprintService;
import pl.edu.wsb.projectmanagement.service.TaskService;
import pl.edu.wsb.projectmanagement.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private SprintService sprintService;
    private UserService userService;

    public TaskController(TaskService taskService, SprintService sprintService, UserService userService) {
        this.taskService = taskService;
        this.sprintService = sprintService;
        this.userService = userService;
    }

    @GetMapping("/create/{sprintId}")
    public String createTask(@PathVariable int sprintId,Model model){
        Sprint sprint = sprintService.findById(sprintId);
        Set<User> colab = new TreeSet<>(sprint.getProject().getTeamList());
        colab.add(sprint.getProject().getOwner());
        model.addAttribute("users",colab);
        model.addAttribute("sprintId",sprintId);
        return "create_task";
    }

    @ResponseBody
    @PostMapping("/create/{sprintId}")
    public String createTask(@PathVariable int sprintId, Task task,@RequestParam("subTasks") String[] subTasks, int assignee){
        task.setSprint(sprintService.findById(sprintId));
        task.setAssignee(userService.findById(assignee));
        taskService.save(task,subTasks);
        return "redirect:/sprint/info/"+sprintId;
    }


    @GetMapping("/info/{id}")
    public String getTask(@PathVariable int id, Model model){
        model.addAttribute("task",taskService.findById(id));
        return "single_task";
    }


}
