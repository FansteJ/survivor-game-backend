package com.stefan.survivorgamebackend.controller;

import com.stefan.survivorgamebackend.dto.UserProfileDTO;
import com.stefan.survivorgamebackend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getMyProfile() {
        return ResponseEntity.ok(userProfileService.getMyProfile());
    }
}
