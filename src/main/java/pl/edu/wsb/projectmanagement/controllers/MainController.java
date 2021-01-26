package pl.edu.wsb.projectmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
    @RequestMapping("/")
    public String index() {
        return "Hello!";
    }
}
