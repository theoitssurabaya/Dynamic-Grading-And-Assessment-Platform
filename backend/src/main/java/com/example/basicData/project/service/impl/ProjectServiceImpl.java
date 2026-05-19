package com.example.basicData.project.service.impl;

import com.example.basicData.exceptions.notFoundException.NotFoundException;
import com.example.basicData.project.dto.ProjectDto;
import com.example.basicData.project.dto.ProjectResponseDto;
import com.example.basicData.project.dto.ProjectsStudentDto;
import com.example.basicData.project.entity.Project;
import com.example.basicData.project.repository.ProjectRepository;
import com.example.basicData.project.service.ProjectService;
import com.example.basicData.student.entity.Student;
import com.example.basicData.student.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final StudentService studentService;

    public ProjectServiceImpl (ProjectRepository projectRepository, StudentService studentService) {
        this.projectRepository = projectRepository;
        this.studentService = studentService;
    }

    @Override
    public List<ProjectsStudentDto> getProjectsByStudentNrp(Long studentNrp) {
        studentService.getStudentByNrp(studentNrp);
        return projectRepository.findAllByStudentNrp(studentNrp).stream().map(this::mapToProjectsStudentDto).toList();
    }

    @Override
    public ProjectResponseDto createProject(ProjectDto projectDto) {
        Student student = studentService.getStudentByNrp(projectDto.getStudentNrp());
        Project request = projectDto.toEntity(student);
        Project newProject = projectRepository.save(request);
        return mapToProjectResponseDto(newProject);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new NotFoundException("Project id not found"));
    }

    public ProjectResponseDto mapToProjectResponseDto(Project project) {
        ProjectResponseDto response = new ProjectResponseDto();
        response.setId(project.getId());
        response.setProjectName(project.getProjectName());
        response.setStudentName(project.getStudent().getName());
        response.setStudentNrp(project.getStudent().getNrp());
        return response;
    }

    public ProjectsStudentDto mapToProjectsStudentDto(Project project) {
        ProjectsStudentDto response = new ProjectsStudentDto();
        response.setId(project.getId());
        response.setName(project.getProjectName());
        response.setNrp(project.getStudent().getNrp());
        return response;
    }
}
