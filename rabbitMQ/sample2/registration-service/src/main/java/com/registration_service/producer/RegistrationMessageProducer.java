package com.registration_service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationMessageProducer {
    private final RabbitTemplate rabbitTemplate;

}
