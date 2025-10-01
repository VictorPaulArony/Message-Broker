package com.registration_service.service;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.registration_service.config.PasswordEncoderConfig;
import com.registration_service.dto.UserRegistrationEvent;
import com.registration_service.dto.UserRequest;
import com.registration_service.exception.FieldAlreadyExistsException;
import com.registration_service.model.User;
import com.registration_service.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new FieldAlreadyExistsException("email",request.getEmail());
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

        //send registion event to rabbitMQ
        UserRegistrationEvent event = new UserRegistrationEvent();
        event.getUserId();
        event.getUsername();
        event.getEmail();

        return savedUser;
    }

    

        

    
}
