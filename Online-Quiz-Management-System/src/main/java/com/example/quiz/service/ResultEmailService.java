package com.example.quiz.service;

import com.example.quiz.model.QuizResult;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResultEmailService {
    private final JavaMailSender mailSender;
    private final String from;

    public ResultEmailService(JavaMailSender mailSender,
            @Value("${app.mail.from:${spring.mail.username:}}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public void sendResult(QuizResult result) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (from != null && !from.isBlank()) message.setFrom(from);
        message.setTo(result.getEmail());
        message.setSubject("Your Online Quiz Result");
        message.setText("Hello " + result.getStudentName() + ",\n\n"
            + "Thank you for completing the quiz. Here is your result:\n\n"
            + "Score: " + result.getScore() + "%\n"
            + "Correct answers: " + result.getCorrectAnswers() + " out of "
            + result.getTotalQuestions() + "\n"
            + "Submitted: " + result.getQuizDate() + "\n\n"
            + "Regards,\nOnline Quiz Management System");
        mailSender.send(message);
    }
}
