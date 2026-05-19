package com.example.basicData.parameter.dto;

import com.example.basicData.aspect.dto.AspectRequestDto;
import com.example.basicData.parameter.entity.Parameter;
import com.example.basicData.score.entity.Score;
import lombok.Data;

import java.util.List;

@Data
public class ParameterRequestDto {
    public String formName;
    public Long scoreId;
    public List<AspectRequestDto> fields;

    public Parameter toEntity(Score score) {
        Parameter parameter = new Parameter();
        parameter.setFormName(formName);
        parameter.setScore(score);
        return parameter;
    }
}
