package com.example.basicData.student.repository;

import com.example.basicData.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByNrp(Long nrp);
}
