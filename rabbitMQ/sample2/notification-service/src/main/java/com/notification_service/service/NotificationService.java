package com.notification_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.notification_service.dto.UserRegistrationEvent;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendNotification(UserRegistrationEvent event) {
        log.info("Sending email to {}", event.getEmail());
        try{
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(event.getEmail());
        message.setSubject(event.getUsername() + " : Welcome to Our Platform!");
        message.setText(String.format("Body: Dear %s, Welcome to our platform! We're excited to have you.",
                event.getUsername()));

        javaMailSender.send(message);
        log.info("Email sent successfully to {}", event.getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", event.getEmail(), e.getMessage());
        }
    }
}
