package ru.kata.spring.boot_security.demo.configs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.security.core.userdetails.User;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String status = "OPEN"; // OPEN, IN_PROGRESS, COMPLETED
    @ManyToOne
    private User assignedTo;

    public void setDescription(String description){ this.description = description;}
    public void getDescription(String description){this.description = description;}

    public void setTitle(String title){this.title=title;}
    public void getTitle(String title){this.title=title;}

    public void setId(Long id){this.id = id;}
    public void getId(Long id){this.id = id;}

    public void setStatus(String status){this.status = status;}
    public void getStatus(String status){this.status = status;}


    public void setAssignedTo(){}
    public void getAssignedTo(){}
}