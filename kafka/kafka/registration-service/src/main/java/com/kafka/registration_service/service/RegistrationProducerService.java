package com.kafka.registration_service.service;

import com.kafka.registration_service.config.PasswordEncoderConfig;
import com.kafka.registration_service.dto.UserRegistrationEvent;
import com.kafka.registration_service.dto.UserRegistrationRequest;
import com.kafka.registration_service.model.UserRegistration;
import com.kafka.registration_service.repository.UserRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationProducerService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate;
    private final PasswordEncoderConfig passwordEncoderConfig;
    @Value("${kafka.topic.name}")
    private String topicName;

    public UserRegistration registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail()) || userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("user already exist");
        }

        String encryptPass = passwordEncoderConfig.passwordEncoder().encode(request.getPassword());
        UserRegistration user = new UserRegistration();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encryptPass);
        user.setTimeStamp(new Date());



        UserRegistrationEvent event = new UserRegistrationEvent();
                event.setUserId(user.getUserId());
                event.setUserName(user.getUserName());
                event.setEmail(user.getEmail());

        kafkaTemplate.send(topicName, event )
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Sent message=[{}] with offset=[{}]",
                                event.getUserName(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("Unable to send message=[{}] due to: {}",
                                event.getUserName(),
                                ex.getMessage());
                    }
                    log.info("registration message sent to the kafka ok {}", event);
                });
        return user;

    }

}
