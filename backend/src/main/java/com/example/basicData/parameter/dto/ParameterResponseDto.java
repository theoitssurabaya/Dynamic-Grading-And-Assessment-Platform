package com.example.basicData.parameter.dto;

import com.example.basicData.aspect.dto.AspectResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ParameterResponseDto {
    private Long id;
    private String formName;
    private List<AspectResponseDto> fields;
    private Integer formTotal;
}
