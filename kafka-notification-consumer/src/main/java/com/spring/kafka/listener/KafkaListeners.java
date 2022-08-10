package com.spring.kafka.listener;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "test", groupId = "groupId")
    void listener(String data) {
        System.out.println("Message received " + data);

    }
}
