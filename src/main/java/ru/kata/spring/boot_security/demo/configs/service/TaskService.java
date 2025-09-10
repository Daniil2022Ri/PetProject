package ru.kata.spring.boot_security.demo.configs.service;

import ru.kata.spring.boot_security.demo.configs.model.Task;
import ru.kata.spring.boot_security.demo.configs.model.User;
import ru.kata.spring.boot_security.demo.configs.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByAssignedTo(user);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}