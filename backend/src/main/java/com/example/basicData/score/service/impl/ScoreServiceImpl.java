package com.example.basicData.score.service.impl;

import com.example.basicData.aspect.dto.AspectRequestDto;
import com.example.basicData.aspect.dto.AspectResponseDto;
import com.example.basicData.aspect.entity.Aspect;
import com.example.basicData.aspect.repository.AspectRepository;
import com.example.basicData.exceptions.notFoundException.NotFoundException;
import com.example.basicData.parameter.dto.ParameterRequestDto;
import com.example.basicData.parameter.dto.ParameterResponseDto;
import com.example.basicData.parameter.entity.Parameter;
import com.example.basicData.parameter.repository.ParameterRepository;
import com.example.basicData.project.dto.ProjectResponseDto;
import com.example.basicData.project.entity.Project;
import com.example.basicData.project.repository.ProjectRepository;
import com.example.basicData.score.dto.ScoreRequestDto;
import com.example.basicData.score.dto.ScoreResponseDto;
import com.example.basicData.score.entity.Score;
import com.example.basicData.score.repository.ScoreRepository;
import com.example.basicData.score.service.ScoreService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class ScoreServiceImpl implements ScoreService {
    private final ProjectRepository projectRepository;
    private final ScoreRepository scoreRepository;
    private final ParameterRepository parameterRepository;
    private final AspectRepository aspectRepository;

    public ScoreServiceImpl(ProjectRepository projectRepository,
                            ScoreRepository scoreRepository,
                            ParameterRepository parameterRepository,
                            AspectRepository aspectRepository) {
        this.projectRepository = projectRepository;
        this.scoreRepository = scoreRepository;
        this.parameterRepository = parameterRepository;
        this.aspectRepository = aspectRepository;
    }

    @Override
    public ScoreResponseDto createScore(ScoreRequestDto scoreRequestDto) {
        // 1. Find project
        Project project = projectRepository.findById(scoreRequestDto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found"));

        // 2. Create Score
//        Score score = new Score();
//        score.setNote(request.getNote());
//        score.setSign(request.getSign());
//        score.setProject(project);
        Score score = scoreRepository.save(scoreRequestDto.toEntity(project));

        List<Parameter> savedParameters = new ArrayList<>();

        // 3. For each parameter in request
        for (ParameterRequestDto paramReq : scoreRequestDto.getParameters()) {
            Parameter parameter = new Parameter();
            parameter.setFormName(paramReq.getFormName());
            parameter.setScore(score); // link to Score

            List<Aspect> aspects = new ArrayList<>();
            int totalScore = 0;

            // 4. For each field, create Aspect
            for (AspectRequestDto field : paramReq.getFields()) {
                Aspect aspect = new Aspect();
                aspect.setName(field.getName());
                aspect.setScore(field.getScore());
                aspect.setParameter(parameter);
                aspects.add(aspect);

                totalScore += field.getScore();
            }
            parameter.setFormTotal(totalScore);
            parameter.setAspects(aspects);
            parameter = parameterRepository.save(parameter);
            aspectRepository.saveAll(aspects);

            savedParameters.add(parameter);
        }

        score.setParameters(savedParameters);
        return mapToDto(score, savedParameters);
    }

    @Override
    public ScoreResponseDto getScoreByProjectId(Long projectId) {
        Score score = scoreRepository.findByProjectId(projectId)
                .orElseThrow(() -> new NotFoundException("Score not found"));

        List<Parameter> parameters = parameterRepository.findByScore(score); // If not fetched by default

        return mapToDto(score, parameters);
    }

    private ScoreResponseDto mapToDto(Score score, List<Parameter> parameters) {
        ScoreResponseDto dto = new ScoreResponseDto();
        dto.setId(score.getId());
        dto.setNote(score.getNote());
        dto.setSign(score.getSign());

        ProjectResponseDto projectDto = new ProjectResponseDto();
        projectDto.setId(score.getProject().getId());
        projectDto.setProjectName(score.getProject().getProjectName());
        projectDto.setStudentName(score.getProject().getStudent().getName());
        projectDto.setStudentNrp(score.getProject().getStudent().getNrp());
        dto.setProject(projectDto);

//        Hitung Bobot
        List<ParameterResponseDto> paramDtos = new ArrayList<>();
        int totalChapterWeight = 0;

        for (Parameter param : parameters) {
            ParameterResponseDto pDto = new ParameterResponseDto();
            pDto.setId(param.getId());
            pDto.setFormName(param.getFormName());
            pDto.setFormTotal(param.getFormTotal());

            List<AspectResponseDto> aspectDtos = param.getAspects().stream().map(a -> {
                AspectResponseDto aDto = new AspectResponseDto();
                aDto.setId(a.getId());
                aDto.setName(a.getName());
                aDto.setScore(a.getScore());
                return aDto;
            }).toList();

            pDto.setFields(aspectDtos);

            // Calculate formTotal (sum of aspect scores as integers)
            int formTotal = param.getAspects().stream()
                    .mapToInt(a -> {
                        try {
                            return (a.getScore());
                        } catch (NumberFormatException e) {
                            return 0; // or throw if you want strict parsing
                        }
                    }).sum();

            totalChapterWeight += formTotal;
            pDto.setFormTotal(formTotal); // Ensure formTotal exists in ParameterResponseDto
            paramDtos.add(pDto);
        }

        dto.setParameters(paramDtos);
        dto.setChapterWeight(totalChapterWeight);

        int chapterWeightedScore = 90 - totalChapterWeight;
        dto.setChapterWeightedScore(chapterWeightedScore);

        // Grade logic
        String grade;
        if (chapterWeightedScore >= 86 && chapterWeightedScore <= 90) {
            grade = "A";
        } else if (chapterWeightedScore >= 78 && chapterWeightedScore <= 85) {
            grade = "B";
        } else if (chapterWeightedScore >= 65 && chapterWeightedScore <= 77) {
            grade = "C";
        } else {
            grade = "D";
        }
        dto.setGrade(grade);

        // Status logic
        String status = grade.equals("D") ? "Ulang" : "Lanjut";
        dto.setStatus(status);
        return dto;
    }
}
