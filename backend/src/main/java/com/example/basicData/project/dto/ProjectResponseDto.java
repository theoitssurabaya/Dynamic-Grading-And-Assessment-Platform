package com.example.basicData.project.dto;

import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String projectName;
    private String studentName;
    private Long studentNrp;

}
