package com.spring.kafka.service;

import com.spring.kafka.config.MailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    MailConfig mailConfig = new MailConfig();
    private final JavaMailSender javaMailSender = mailConfig.gmailMailSender();

    @Value("${spring.mail.username}")
    private String smtpServer;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(smtpServer);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}

