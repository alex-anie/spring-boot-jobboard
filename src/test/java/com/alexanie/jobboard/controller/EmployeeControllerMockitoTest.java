package com.alexanie.jobboard.controller;

import com.alexanie.jobboard.model.Employee;
import com.alexanie.jobboard.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // enables Mockito in JUnit
class EmployeeControllerMockitoTest {

    @Mock
    private EmployeeService service;  // fake service

    @InjectMocks
    private EmployeeController controller; // real controller with injected mock

    @Test
    @DisplayName("Find employee by ID using only Mockito")
    void getEmployeeById_returnsEmployee() {
        // Given
        Employee employee = new Employee(1L, "Alice", "Backend Developer", 90000.0, List.of("Java", "Spring Boot"));
        when(service.getEmployeeById(anyLong())).thenReturn(employee);

        // When
        Employee result = controller.getEmployeeById(1L);

        // Then
        assertEquals("Alice", result.getName());
        assertEquals("Backend Developer", result.getRole());
    }
}
