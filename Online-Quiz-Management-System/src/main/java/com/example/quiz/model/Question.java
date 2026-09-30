package com.example.quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "questions")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String questionText;
    @NotBlank private String optionA;
    @NotBlank private String optionB;
    @NotBlank private String optionC;
    @NotBlank private String optionD;
    @NotBlank private String correctAnswer;
    private String category = "General";

    public Question() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getQuestionText(){return questionText;} public void setQuestionText(String v){this.questionText=v;}
    public String getOptionA(){return optionA;} public void setOptionA(String v){this.optionA=v;}
    public String getOptionB(){return optionB;} public void setOptionB(String v){this.optionB=v;}
    public String getOptionC(){return optionC;} public void setOptionC(String v){this.optionC=v;}
    public String getOptionD(){return optionD;} public void setOptionD(String v){this.optionD=v;}
    public String getCorrectAnswer(){return correctAnswer;} public void setCorrectAnswer(String v){this.correctAnswer=v;}
    public String getCategory(){return category;} public void setCategory(String v){this.category=(v==null||v.isBlank())?"General":v;}
}
