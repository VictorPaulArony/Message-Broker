package com.registration_service.service;

import com.registration_service.dto.UserRequest;
import com.registration_service.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(UserRequest request) {
        

        

    
}
