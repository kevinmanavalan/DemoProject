package com.example.demo.Student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudent() {
        Student obj = new Student(
                "Kevin",
                "kevinsmanavalan@gmail.com",
                LocalDate.of(2000, 1, 11),
                24);
        return List.of(obj);
    }
}
