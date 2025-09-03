package com.alexanie.jobboard.service;

import com.alexanie.jobboard.repository.EmployeeRepository;
import com.alexanie.jobboard.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return repository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setRole(employeeDetails.getRole());
            employee.setSalary(employeeDetails.getSalary());
            employee.setSkills(employeeDetails.getSkills());
            return repository.save(employee);
        }).orElse(null);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}