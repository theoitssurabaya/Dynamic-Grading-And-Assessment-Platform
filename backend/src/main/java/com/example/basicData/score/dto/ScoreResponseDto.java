package com.example.basicData.score.dto;

import com.example.basicData.parameter.dto.ParameterResponseDto;
import com.example.basicData.project.dto.ProjectResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ScoreResponseDto {
    private Long id;
    private String note;
    private String sign;
    private List<ParameterResponseDto> parameters;
    private ProjectResponseDto project;
    private Integer chapterWeight;
    private Integer chapterWeightedScore;
    private String grade;
    private String status;
}
