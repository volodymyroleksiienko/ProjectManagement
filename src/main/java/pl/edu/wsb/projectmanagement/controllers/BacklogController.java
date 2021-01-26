package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Backlog;
import pl.edu.wsb.projectmanagement.service.BacklogService;
import pl.edu.wsb.projectmanagement.service.ProjectService;

@Controller
@RequestMapping("/backlog")
public class BacklogController {
    private BacklogService backlogService;
    private ProjectService projectService;

    public BacklogController(BacklogService backlogService, ProjectService projectService) {
        this.backlogService = backlogService;
        this.projectService = projectService;
    }

    @PostMapping("/create/{projectId}")
    public String createBacklog(@PathVariable int projectId, Backlog backlog){
        backlog.setProject(projectService.findById(projectId));
        backlogService.save(backlog);
        return "redirect:/projects/info/"+projectId;
    }

    @PostMapping("/update/{projectId}")
    public String updateBacklog(@PathVariable int projectId, Backlog backlog){
        backlog.setProject(projectService.findById(projectId));
        backlogService.update(backlog);
        return "redirect:/projects/info/"+projectId;
    }

    @PostMapping("/delete/{projectId}")
    public String deleteBacklog(@PathVariable int projectId, int id){
        backlogService.deleteByID(id);
        return "redirect:/projects/info/"+projectId;
    }
}
