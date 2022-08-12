package com.spring.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender email;

    @Autowired
    public EmailService(JavaMailSender email) {
        this.email = email;
    }

    @Value("${spring.mail.username}")
    private String smtpServer;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(smtpServer);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        email.send(message);
    }
}

