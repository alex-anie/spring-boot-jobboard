package com.alexanie.jobboard.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private Double salary; // in USD

    @ElementCollection
    private List<String> skills; // tech-related skills

    // Default constructor (needed by JPA)
    public Employee() {}

    // Convenience constructor for dummy data
    public Employee(Long id, String name, String role, Double salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.skills = skills;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}