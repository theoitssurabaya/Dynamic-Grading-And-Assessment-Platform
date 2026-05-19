package com.example.basicData.project.controller;

import com.example.basicData.project.dto.ProjectDto;
import com.example.basicData.project.dto.ProjectResponseDto;
import com.example.basicData.project.dto.ProjectsStudentDto;
import com.example.basicData.project.service.ProjectService;
import com.example.basicData.response.Response;
import com.example.basicData.student.dto.StudentDto;
import com.example.basicData.student.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@Validated
@Log
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController (ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{nrp}")
    public ResponseEntity<Response<List<ProjectsStudentDto>>> getProjectsByStudentsNrp(@PathVariable Long nrp) {
        return Response.successResponse("All student's projects", projectService.getProjectsByStudentNrp(nrp));
    }

    @PostMapping
    public ResponseEntity<Response<ProjectResponseDto>> signup(@RequestBody ProjectDto projectDto) {
        return Response.successResponse("Add new project success", projectService.createProject(projectDto));
    }
}
