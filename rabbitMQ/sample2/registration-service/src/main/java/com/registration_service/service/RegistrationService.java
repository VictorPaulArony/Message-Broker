package com.registration_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration_service.dto.UserRegistrationEvent;
import com.registration_service.dto.UserRequest;
import com.registration_service.exception.FieldAlreadyExistsException;
import com.registration_service.model.User;
import com.registration_service.producer.RegistrationMessageProducer;
import com.registration_service.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationMessageProducer registrationMessageProducer;

    @Transactional
    public User registerUser(UserRequest request) {
        System.out.println("Registering user: " + request.getUsername());
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new FieldAlreadyExistsException("email", request.getEmail());
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new FieldAlreadyExistsException("username", request.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        // send registion event to rabbitMQ
        UserRegistrationEvent event = new UserRegistrationEvent();
        event.setUserId(savedUser.getId());
        event.setUsername(savedUser.getUsername());
        event.setEmail(savedUser.getEmail());

        registrationMessageProducer.sendRegistrationEvent(event);
        System.out.println("ok");

        return savedUser;
    }

}
