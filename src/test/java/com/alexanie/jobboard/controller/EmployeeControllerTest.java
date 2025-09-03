package com.alexanie.jobboard.controller;

import com.alexanie.jobboard.model.Employee;
import com.alexanie.jobboard.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Job Board Unit Test")
class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController employeeController;

    @Nested
    @DisplayName("Run Employee Controller Tests")
    class EmployeeTest {

        @Test
        @DisplayName("Get all employees from the controller")
        void getAllEmployees_returnsListOfEmployees() {
            // Given
            List<Employee> employees = Arrays.asList(
                    new Employee(1L, "Alice", "Backend Dev", 90000.0, Arrays.asList("Java", "Spring Boot")),
                    new Employee(2L, "Bob", "Frontend Dev", 85000.0, Arrays.asList("React", "CSS"))
            );
            when(service.getAllEmployees()).thenReturn(employees);

            // When
            List<Employee> result = employeeController.getAllEmployees();

            // Then
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Alice");
            verify(service, times(1)).getAllEmployees();
        }

        @Test
        @DisplayName("Get employee by ID - success case")
        void getEmployeeById_returnsEmployeeIfExists() {
            // Given
            Employee employee = new Employee(1L, "Alice", "Backend Dev", 90000.0, Arrays.asList("Java"));
            when(service.getEmployeeById(1L)).thenReturn(employee);

            // When
            Employee result = employeeController.getEmployeeById(1L);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo("Alice");
            verify(service, times(1)).getEmployeeById(1L);
        }

        @Test
        @DisplayName("Get employee by ID - not found case")
        void getEmployeeById_returnsNullIfNotFound() {
            // Given
            when(service.getEmployeeById(99L)).thenReturn(null);

            // When
            Employee result = employeeController.getEmployeeById(99L);

            // Then
            assertThat(result).isNull();
            verify(service, times(1)).getEmployeeById(99L);
        }

        @Test
        @DisplayName("Add a new employee successfully")
        void addEmployee_savesEmployeeSuccessfully() {
            // Given
            Employee newEmployee = new Employee(null, "Charlie", "DevOps", 100000.0, Arrays.asList("Docker"));
            Employee savedEmployee = new Employee(3L, "Charlie", "DevOps", 100000.0, Arrays.asList("Docker"));
            when(service.addEmployee(newEmployee)).thenReturn(savedEmployee);

            // When
            Employee result = employeeController.addEmployee(newEmployee);

            // Then
            assertThat(result.getId()).isEqualTo(3L);
            assertThat(result.getName()).isEqualTo("Charlie");
            verify(service, times(1)).addEmployee(newEmployee);
        }
    }
}
