package com.example.basicData.student.service.impl;

import com.example.basicData.exceptions.notFoundException.NotFoundException;
import com.example.basicData.student.dto.StudentDto;
import com.example.basicData.student.entity.Student;
import com.example.basicData.student.repository.StudentRepository;
import com.example.basicData.student.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Log
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student request = studentDto.toEntity();
        Student newStudent = studentRepository.save(request);
        return mapToStudentDto(newStudent);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(this::mapToStudentDto).toList();
    }

    @Override
    public Student getStudentByNrp(Long nrp) {
        return studentRepository.findByNrp(nrp).orElseThrow(()->new NotFoundException("Student id not found"));
    }

    public StudentDto mapToStudentDto(Student student) {
        StudentDto response = new StudentDto();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setNrp(student.getNrp());
        return response;
    }
}
