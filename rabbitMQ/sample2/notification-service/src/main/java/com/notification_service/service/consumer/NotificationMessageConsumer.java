package com.notification_service.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.notification_service.dto.UserRegistrationEvent;
import com.notification_service.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationMessageConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleRegistrationEvent(UserRegistrationEvent event) {
        try {
            log.info("Notification sent to user: {}", event.getEmail());
            notificationService.sendNotification(event);
        } catch (Exception e) {
            log.error("Error processing notification: {}", e.getMessage());
        }
    }
    
}
