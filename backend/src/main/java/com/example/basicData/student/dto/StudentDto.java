package com.example.basicData.student.dto;

import com.example.basicData.student.entity.Student;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Long nrp;

    public Student toEntity(){
        Student student = new Student();
        student.setName(name);
        student.setNrp(nrp);

        return student;
    }
}
