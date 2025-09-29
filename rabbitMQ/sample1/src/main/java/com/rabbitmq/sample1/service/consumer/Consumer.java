package com.rabbitmq.sample1.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receivedMessage(String message) {
        log.info("Message Received by Consumer: {}", message);
    }
}
