package com.kafka.notification_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    // Configuration is handled by application.yml
    // This class is optional but good for organization
}