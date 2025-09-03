package com.alexanie.jobboard;

import com.alexanie.jobboard.model.Employee;
import com.alexanie.jobboard.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class JobBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobBoardApplication.class, args);
    }

    // Load dummy data into the DB on startup
    @Bean
    CommandLineRunner runner(EmployeeRepository repository) {
        return args -> {
            repository.saveAll(Arrays.asList(
                    new Employee(null, "Alice Johnson", "Backend Developer", 95000.0,
                            Arrays.asList("Java", "Spring Boot", "SQL")),

                    new Employee(null, "Bob Smith", "Frontend Developer", 87000.0,
                            Arrays.asList("JavaScript", "React", "CSS")),

                    new Employee(null, "Cynthia Lee", "DevOps Engineer", 105000.0,
                            Arrays.asList("AWS", "Docker", "Kubernetes")),

                    new Employee(null, "David Kim", "Data Scientist", 115000.0,
                            Arrays.asList("Python", "TensorFlow", "Pandas")),

                    new Employee(null, "Emma Brown", "Full Stack Developer", 99000.0,
                            Arrays.asList("Java", "Angular", "PostgreSQL"))
            ));
        };
    }

}
