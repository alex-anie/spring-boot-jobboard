package com.alexanie.jobboard.repository;

import com.alexanie.jobboard.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}