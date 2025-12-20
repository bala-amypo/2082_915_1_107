package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserServiceImpl {

    private final UserRepository userRepository;

    // REQUIRED constructor (tests depend on this)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // default role if null
        if (user.getRole() == null) {
            user.setRole("AGENT");
        }

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
