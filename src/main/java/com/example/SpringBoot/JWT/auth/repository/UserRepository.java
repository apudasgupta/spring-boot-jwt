package com.example.SpringBoot.JWT.auth.repository;

import com.example.SpringBoot.JWT.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userName);
    Optional<User> findById(Long id);
}
