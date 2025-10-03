package com.kafka.registration_service.controller;

import com.kafka.registration_service.dto.UserRegistrationRequest;
import com.kafka.registration_service.service.RegistrationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationProducerService registrationProducerService;

    @PostMapping
    public String handleRegistration(@RequestBody UserRegistrationRequest request) {
        registrationProducerService.registerUser(request);
        return "User registration event published successfully";
    }
}
