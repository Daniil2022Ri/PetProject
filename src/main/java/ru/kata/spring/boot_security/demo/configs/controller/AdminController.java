package ru.kata.spring.boot_security.demo.configs.controller;

import ru.kata.spring.boot_security.demo.configs.service.TaskService;
import ru.kata.spring.boot_security.demo.configs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final TaskService taskService;
    private final UserService userService;

    public AdminController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("users", userService.findAll());
        return "admin";
    }
}