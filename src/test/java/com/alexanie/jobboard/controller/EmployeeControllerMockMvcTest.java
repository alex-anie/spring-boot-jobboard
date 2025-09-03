package com.alexanie.jobboard.controller;

import com.alexanie.jobboard.model.Employee;
import com.alexanie.jobboard.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)  // Loads only the controller layer
class EmployeeControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService service; // Mock service layer

    @Autowired
    private ObjectMapper objectMapper; // For JSON conversion

    @Test
    @DisplayName("GET /api/employees returns list of employees")
    void getAllEmployees_returnsJsonArray() throws Exception {
        // Given
        when(service.getAllEmployees()).thenReturn(Arrays.asList(
                new Employee(1L, "Alice", "Backend Dev", 90000.0, Arrays.asList("Java")),
                new Employee(2L, "Bob", "Frontend Dev", 85000.0, Arrays.asList("React"))
        ));

        // When + Then
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].role").value("Frontend Dev"));
    }

    @Test
    @DisplayName("POST /api/employees creates a new employee")
    void addEmployee_createsNewEmployee() throws Exception {
        // Given
        Employee newEmployee = new Employee(null, "Charlie", "DevOps", 100000.0, Arrays.asList("Docker"));
        Employee savedEmployee = new Employee(3L, "Charlie", "DevOps", 100000.0, Arrays.asList("Docker"));
        when(service.addEmployee(newEmployee)).thenReturn(savedEmployee);

        // When + Then
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.role").value("DevOps"));
    }
}
