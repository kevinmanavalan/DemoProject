package com.example.demo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> newStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(newStudent.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student not found");
        }
        studentRepository.deleteById(id);
    }
    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(()->new IllegalStateException("Student of id: "+ id +" does not exist"));
        if(name != null && name.length() > 0 && !name.equals(student.getName())){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !email.equals(student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }
}
