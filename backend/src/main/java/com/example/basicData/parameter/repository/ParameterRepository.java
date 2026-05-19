package com.example.basicData.parameter.repository;

import com.example.basicData.parameter.entity.Parameter;
import com.example.basicData.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    List<Parameter> findByScore(Score score);
}
