package com.VKU.ExpenseManagement.controller;

import com.VKU.ExpenseManagement.dto.StudentDTO;
import com.VKU.ExpenseManagement.entities.Student;
import com.VKU.ExpenseManagement.repository.StudentRepository;
import com.VKU.ExpenseManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO s){
        StudentDTO studentDTO = studentService.createStudent(s);
        return ResponseEntity.ok(studentDTO);
    }

}
