package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student kevin = new Student(
                    "Kevin",
                    "kevinsmanavalan@gmail.com",
                    LocalDate.of(2000, 1, 11));
            Student david = new Student(
                    "David",
                    "davidmanavalan@gmail.com",
                    LocalDate.of(2013, 1, 11));
            studentRepository.saveAll(List.of(kevin, david));
        };
    }
}
