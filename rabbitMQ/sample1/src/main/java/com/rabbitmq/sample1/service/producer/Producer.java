package com.rabbitmq.sample1.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.Key}")
    private String routingKey;


    //use rabbit template to send the messages
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        log.info("Message sent by producer: {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
