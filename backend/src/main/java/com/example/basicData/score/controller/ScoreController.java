package com.example.basicData.score.controller;

import com.example.basicData.response.Response;
import com.example.basicData.score.dto.ScoreRequestDto;
import com.example.basicData.score.dto.ScoreResponseDto;
import com.example.basicData.score.entity.Score;
import com.example.basicData.score.service.ScoreService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/scores")
@Validated
@Log
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public ResponseEntity<Response<ScoreResponseDto>> createScore(@RequestBody ScoreRequestDto request) {
        return Response.successResponse("Create score success", scoreService.createScore(request));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Response<ScoreResponseDto>> createScore(@PathVariable Long projectId) {
        return Response.successResponse("Get score success", scoreService.getScoreByProjectId(projectId));
    }
}
