package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.repository.QuestionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionRepository repository;
    public QuestionController(QuestionRepository repository){this.repository=repository;}
    @GetMapping public List<Question> all(){return repository.findAll();}
    @PostMapping public Question create(@Valid @RequestBody Question question){
        String answer=question.getCorrectAnswer()==null?"":question.getCorrectAnswer().trim().toUpperCase();
        if(!List.of("A","B","C","D").contains(answer)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"correctAnswer must be A, B, C or D");
        question.setCorrectAnswer(answer); question.setId(null); return repository.save(question);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Question not found");
        repository.deleteById(id);
    }
}
