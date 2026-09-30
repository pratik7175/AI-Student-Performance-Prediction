package com.example.quiz.repository;
import com.example.quiz.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {}
