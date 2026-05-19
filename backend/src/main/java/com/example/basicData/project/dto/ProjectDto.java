package com.example.basicData.project.dto;

import com.example.basicData.project.entity.Project;
import com.example.basicData.student.entity.Student;
import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private Long studentNrp;
    private String projectName;

    public Project toEntity(Student student) {
        Project project = new Project();
        project.setId(id);
        project.setStudent(student);
        project.setProjectName(projectName);
        return project;
    }
}
