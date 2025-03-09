package com.VKU.ExpenseManagement.service;

import com.VKU.ExpenseManagement.dto.StudentDTO;
import com.VKU.ExpenseManagement.entities.Student;
import com.VKU.ExpenseManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

//    public StudentDTO createStudent(StudentDTO s){
//        Student student = new Student();
//        student.setName(s.getName());
//        student.setAge(s.getAge());
//        student.setAddress(s.getAddress());
//
//        studentRepository.save(student);
//
//        return new StudentDTO(student.getName(),student.getAge(),student.getAddress());
//    }

    public StudentDTO createStudent(StudentDTO s){
        Student student = Student.builder()
                .name(s.getName())
                .age(s.getAge())
                .address(s.getAddress())
                .build();

        studentRepository.save(student);

        return StudentDTO.builder()
                .name(student.getName())
                .age(student.getAge())
                .address(student.getAddress())
                .build();
    }


}
