package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Project;
import pl.edu.wsb.projectmanagement.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String getAllProjects(Model model){
        model.addAttribute("projects",projectService.findAll());
        return "index";
    }

    @PostMapping("/create")
    public String addProject(Project project){
        projectService.save(project);
        return "redirect:/projects";
    }

    @PostMapping("/update")
    public String updateProject(Project project){
        projectService.update(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete")
    public String deleteProject(int id){
        projectService.deleteByID(id);
        return "redirect:/projects";
    }

    @GetMapping("/info/{id}")
    public String getProjectInfo(@PathVariable int id,Model model){
        model.addAttribute("project",projectService.findById(id));
        return "project_backlogs_sprints";
    }
}
