package ru.kata.spring.boot_security.demo.configs.repository;

import ru.kata.spring.boot_security.demo.configs.model.Task;
import ru.kata.spring.boot_security.demo.configs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user);
}