package com.registration_service.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.registration_service.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long>{
    
}
