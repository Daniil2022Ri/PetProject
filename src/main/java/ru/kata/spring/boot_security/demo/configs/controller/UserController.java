package ru.kata.spring.boot_security.demo.configs.controller;

import ru.kata.spring.boot_security.demo.configs.model.User;
import ru.kata.spring.boot_security.demo.configs.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final TaskService taskService;

    public UserController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("tasks", taskService.findByUser(user));
        return "user";
    }
}