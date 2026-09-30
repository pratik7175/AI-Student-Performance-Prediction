package com.example.quiz.config;

import com.example.quiz.model.Question;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seedQuestions(QuestionRepository repo){return args->{
        if(repo.count()!=0)return;
        add(repo,"Which language is primarily used with Spring Boot?","Java","Python","C","PHP","A","Java");
        add(repo,"Which HTML tag creates a hyperlink?","<link>","<a>","<href>","<url>","B","Web Development");
        add(repo,"Which database is configured for this project?","MySQL","MongoDB","H2","Oracle","C","Database");
        add(repo,"What does CSS stand for?","Computer Style Sheets","Creative Style System","Cascading Style Sheets","Colorful Style Syntax","C","Web Development");
        add(repo,"Which keyword creates a class in Java?","define","class","struct","object","B","Java");
        add(repo,"Which HTTP method is commonly used to retrieve data?","POST","PUT","DELETE","GET","D","Web");
        add(repo,"Which collection does not allow duplicate elements in Java?","List","Set","ArrayList","Queue","B","Java");
        add(repo,"What does API stand for?","Application Programming Interface","Applied Program Internet","Application Process Integration","Advanced Programming Input","A","General");
        add(repo,"Which symbol ends most Java statements?",":",".",";",",","C","Java");
        add(repo,"Which JavaScript keyword declares a block-scoped variable that can be reassigned?","const","let","static","final","B","JavaScript");
    };}
    private void add(QuestionRepository r,String q,String a,String b,String c,String d,String answer,String category){Question x=new Question();x.setQuestionText(q);x.setOptionA(a);x.setOptionB(b);x.setOptionC(c);x.setOptionD(d);x.setCorrectAnswer(answer);x.setCategory(category);r.save(x);}
}
