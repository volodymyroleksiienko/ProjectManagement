package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.service.ProjectService;

@Controller
@RequestMapping("/team")
public class TeamController {
    private ProjectService projectService;

    public TeamController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public String getTeamOfProject(@PathVariable int id, Model model){
        model.addAttribute("project",projectService.findById(id));
        return "team";
    }
}
