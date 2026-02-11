package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        userRepository.save(new User(username, passwordEncoder.encode(password)));
    }
}
