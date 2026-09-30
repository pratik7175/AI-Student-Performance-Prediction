package com.example.quiz.controller;

import com.example.quiz.model.QuizResult;
import com.example.quiz.repository.QuizResultRepository;
import com.example.quiz.service.ResultEmailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final QuizResultRepository repository;
    private final ResultEmailService emailService;

    public ResultController(QuizResultRepository repository, ResultEmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @GetMapping
    public List<QuizResult> all() {
        return repository.findAll();
    }

    @PostMapping
    public QuizResult create(@RequestBody QuizResult result) {
        if (result.getStudentName() == null || result.getStudentName().isBlank()
                || result.getEmail() == null || result.getEmail().isBlank()
                || result.getTotalQuestions() < 1 || result.getCorrectAnswers() < 0
                || result.getCorrectAnswers() > result.getTotalQuestions()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid quiz result");
        }

        result.setId(null);
        result.setScore((int) Math.round(
            result.getCorrectAnswers() * 100.0 / result.getTotalQuestions()));

        // Persist first: an SMTP failure must not discard the student's quiz result.
        QuizResult saved = repository.save(result);
        try {
            emailService.sendResult(saved);
            saved.setEmailSent(true);
        } catch (Exception ex) {
            saved.setEmailSent(false);
            // Keep the saved result available to the admin even if email is unavailable.
            System.err.println("Could not email quiz result to student: " + ex.getMessage());
        }
        return saved;
    }
}
