package com.example.basicData.student.service;

import com.example.basicData.student.dto.StudentDto;
import com.example.basicData.student.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    Student getStudentByNrp(Long nrp);
}
