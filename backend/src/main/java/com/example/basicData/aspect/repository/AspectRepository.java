package com.example.basicData.aspect.repository;

import com.example.basicData.aspect.entity.Aspect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspectRepository extends JpaRepository<Aspect, Long> {
}
