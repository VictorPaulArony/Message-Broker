package com.kafka.registration_service.dto;

import lombok.Data;

@Data
public class UserRegistrationEvent {
    private Long userId;
    private String userName;
    private String email;
}
