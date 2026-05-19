package com.example.basicData.student.controller;

import com.example.basicData.response.Response;
import com.example.basicData.student.dto.StudentDto;
import com.example.basicData.student.entity.Student;
import com.example.basicData.student.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Validated
@Log
public class StudentController {
    private final StudentService studentService;

    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Response<List<StudentDto>>> getAllStudents() {
        return Response.successResponse("All students", studentService.getAllStudents());
    }

    @GetMapping("/{nrp}")
    public ResponseEntity<Response<Student>> getStudentByNrp(@PathVariable Long nrp) {
        return Response.successResponse("All students", studentService.getStudentByNrp(nrp));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Response<StudentDto>> signup(@RequestBody StudentDto signupRequestDto) {
        return Response.successResponse("Add new student success", studentService.addStudent(signupRequestDto));
    }
}
