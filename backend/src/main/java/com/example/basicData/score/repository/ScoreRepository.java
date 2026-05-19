package com.example.basicData.score.repository;

import com.example.basicData.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByProjectId(Long projectId);
}
