package com.example.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_results")
public class QuizResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String email;
    private int totalQuestions;
    private int correctAnswers;
    private int score;
    private LocalDateTime quizDate;\n    @Transient private Boolean emailSent;
    @PrePersist protected void onCreate(){if(quizDate==null)quizDate=LocalDateTime.now();}
    public QuizResult() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getStudentName(){return studentName;} public void setStudentName(String v){studentName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public int getTotalQuestions(){return totalQuestions;} public void setTotalQuestions(int v){totalQuestions=v;}
    public int getCorrectAnswers(){return correctAnswers;} public void setCorrectAnswers(int v){correctAnswers=v;}
    public int getScore(){return score;} public void setScore(int v){score=v;}
    public LocalDateTime getQuizDate(){return quizDate;} public void setQuizDate(LocalDateTime v){quizDate=v;}\n    public Boolean getEmailSent(){return emailSent;} public void setEmailSent(Boolean v){emailSent=v;}
}
