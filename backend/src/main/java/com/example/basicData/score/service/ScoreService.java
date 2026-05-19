package com.example.basicData.score.service;

import com.example.basicData.score.dto.ScoreRequestDto;
import com.example.basicData.score.dto.ScoreResponseDto;

public interface ScoreService {
    ScoreResponseDto createScore(ScoreRequestDto scoreRequestDto);

    ScoreResponseDto getScoreByProjectId(Long projectId);
}
