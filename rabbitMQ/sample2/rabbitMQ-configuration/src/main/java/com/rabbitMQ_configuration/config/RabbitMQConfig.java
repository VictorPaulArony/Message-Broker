package com.rabbitMQ_configuration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitMQConfig {

    @Value("${RABBITMQ.QUEUE.NAME}")
    private String queue;

    @Value("${RABBITMQ.EXCHANGE.NAME}")
    private String exchange;

    @Value("${RABBITMQ.ROUTING.KEY.NAME}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return new BindingBuilder();
    }

}
