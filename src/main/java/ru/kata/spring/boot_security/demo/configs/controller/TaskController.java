package ru.kata.spring.boot_security.demo.configs.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.configs.model.Task;
import ru.kata.spring.boot_security.demo.configs.model.User;
import ru.kata.spring.boot_security.demo.configs.service.TaskService;
import ru.kata.spring.boot_security.demo.configs.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/createTask")
    public String createTask(@Valid Task task, BindingResult result, @RequestParam Long assignedToId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("tasks", taskService.findAll());
            return "admin"; // Вернуть на admin с ошибками
        }
        Optional<User> assignedTo = userService.findById(assignedToId);
        if (assignedTo.isEmpty()) {
            model.addAttribute("error", "User not found");
            model.addAttribute("users", userService.findAll());
            model.addAttribute("tasks", taskService.findAll());
            return "admin"; // Возврат с ошибкой
        }
        task.setAssignedTo();
        taskService.save(task);
        return "redirect:/admin";
    }

    @GetMapping("/startTask")
    public String startTask(@RequestParam Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setStatus("IN_PROGRESS");
            taskService.save(task);
        }
        return "redirect:/user";
    }

    @GetMapping("/completeTask")
    public String completeTask(@RequestParam Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setStatus("COMPLETED");
            taskService.save(task);
        }
        return "redirect:/user";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }
}