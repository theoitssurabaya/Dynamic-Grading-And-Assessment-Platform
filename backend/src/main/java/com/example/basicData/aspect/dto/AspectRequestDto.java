package com.example.basicData.aspect.dto;

import com.example.basicData.aspect.entity.Aspect;
import com.example.basicData.parameter.entity.Parameter;
import lombok.Data;

@Data
public class AspectRequestDto {
    private String name;
    private Integer score;
    private Long parameterId;

    public Aspect toEntity(Parameter parameter){
        Aspect aspect = new Aspect();
        aspect.setName(name);
        aspect.setScore(score);
        aspect.setParameter(parameter);
        return aspect;
    }
}
