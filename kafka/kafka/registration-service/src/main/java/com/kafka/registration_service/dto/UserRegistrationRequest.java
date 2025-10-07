package com.kafka.registration_service.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String userName;
    private String email;
    private  String password;

}
