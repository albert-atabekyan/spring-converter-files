package com.spring.kafka.listener;


import com.spring.kafka.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {


    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "test", groupId = "groupId")
    void listener(String data) {
        System.out.println("Message received " + data);
        System.out.println(data.split(" ")[0]);
        System.out.println(data.split(" ")[1]);
        emailService.sendSimpleMessage(data.split(" ")[0], "kafka", data.split(" ")[1]);
    }
}
