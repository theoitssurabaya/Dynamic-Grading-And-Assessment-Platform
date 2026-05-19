package com.example.basicData.score.dto;

import com.example.basicData.parameter.dto.ParameterRequestDto;
import com.example.basicData.project.entity.Project;
import com.example.basicData.score.entity.Score;
import lombok.Data;

import java.util.List;

@Data
public class ScoreRequestDto {
    private Long projectId;
    private String note;
    private String sign;
    private List<ParameterRequestDto> parameters;

    public Score toEntity(Project project) {
        Score score = new Score();
        score.setNote(note);
        score.setSign(sign);
        score.setProject(project);
        return score;
    }
}
