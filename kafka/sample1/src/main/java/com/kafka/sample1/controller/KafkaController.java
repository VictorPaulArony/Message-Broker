package com.kafka.sample1.controller;

import com.kafka.sample1.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/kafka/")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public String handleMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "Message published: " + message;
    }
}
