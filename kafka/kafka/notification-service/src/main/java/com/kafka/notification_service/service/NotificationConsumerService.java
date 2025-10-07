package com.kafka.notification_service.service;

import com.kafka.notification_service.dto.UserRegistrationEvent;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    @KafkaListener(topics = "user-registration", groupId = "user-registration")
    public void consumeRegistrationEvent(UserRegistrationEvent event){
        System.out.println("Received registration event for user: " + event.getUserId());
        sendWelcomeNotification(event);
    }

    private void sendWelcomeNotification(UserRegistrationEvent event) {
        // Implement your notification logic here
        System.out.println("Sending welcome email to: " + event.getEmail());
        System.out.println("Welcome " + event.getUserName() + "!");
    }
}
