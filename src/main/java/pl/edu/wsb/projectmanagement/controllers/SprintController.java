package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Sprint;
import pl.edu.wsb.projectmanagement.entity.TaskStatus;
import pl.edu.wsb.projectmanagement.service.ProjectService;
import pl.edu.wsb.projectmanagement.service.SprintService;
import pl.edu.wsb.projectmanagement.service.TaskService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

    private SprintService sprintService;
    private ProjectService projectService;
    private TaskService taskService;

    public SprintController(SprintService sprintService, ProjectService projectService, TaskService taskService) {
        this.sprintService = sprintService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/info/{id}")
    public String getSprintInfo(@PathVariable int id, Model model){
        model.addAttribute("sprint",sprintService.findById(id));
        model.addAttribute("openTasks",taskService.findByTaskStatus(TaskStatus.OPEN));
        model.addAttribute("inProcessTasks",taskService.findByTaskStatus(TaskStatus.IN_PROCESS));
        model.addAttribute("doneTasks",taskService.findByTaskStatus(TaskStatus.DONE));
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
