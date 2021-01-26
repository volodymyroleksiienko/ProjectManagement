package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wsb.projectmanagement.entity.Task;
import pl.edu.wsb.projectmanagement.service.SprintService;
import pl.edu.wsb.projectmanagement.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private SprintService sprintService;

    public TaskController(TaskService taskService, SprintService sprintService) {
        this.taskService = taskService;
        this.sprintService = sprintService;
    }

    @GetMapping("/create/{sprintId}")
    public String getTask(@PathVariable int sprintId, Task task){
//        task.setSprint(sprintService.findById(sprintId));
//        taskService.save(task);
        return "create_task";
//        return "redirect:/sprint/info/"+sprintId;
    }

    @GetMapping("/info/{id}")
    public String getTask(@PathVariable int id, Model model){
        model.addAttribute("task",taskService.findById(id));
        return "single_task";
    }

}
