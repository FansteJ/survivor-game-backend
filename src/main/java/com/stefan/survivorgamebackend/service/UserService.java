package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.repository.UserRepository;
import com.stefan.survivorgamebackend.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        userRepository.save(new User(username, passwordEncoder.encode(password)));
    }

    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent() || !passwordEncoder.matches(password, user.get().getPasswordHash())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return jwtService.generateToken(user.get().getUsername());
    }
}
