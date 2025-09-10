package ru.kata.spring.boot_security.demo.configs.repository;

import ru.kata.spring.boot_security.demo.configs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}