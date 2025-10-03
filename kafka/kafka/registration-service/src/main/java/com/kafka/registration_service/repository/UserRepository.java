package com.kafka.registration_service.repository;

import com.kafka.registration_service.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRegistration, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
