package com.rabbitmq.sample1.controller;

import com.rabbitmq.sample1.service.producer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sms")
@RequiredArgsConstructor
public class MessageController {

    private final Producer producer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }

}
