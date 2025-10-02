package com.registration_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.registration_service.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
