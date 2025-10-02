package com.registration_service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.registration_service.config.RabbitMQConfiguration;
import com.registration_service.dto.UserRegistrationEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfiguration rabbitMQConfiguration;

    public void sendRegistrationEvent(UserRegistrationEvent event) {
        rabbitTemplate.convertAndSend(
            rabbitMQConfiguration.getTopicExchange(),
            rabbitMQConfiguration.getRoutingKey(),
            event
        );
        log.info("Registration event sent successfully");
    }

}
