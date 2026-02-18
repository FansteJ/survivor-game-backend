package com.stefan.survivorgamebackend.controller;


import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserQuest;
import com.stefan.survivorgamebackend.service.QuestService;
import com.stefan.survivorgamebackend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quests")
@AllArgsConstructor
public class QuestController {
    private final QuestService questService;
    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<UserQuest>> getMyQuests() {
        UserProfile profile = userProfileService.getCurrentProfile();
        return ResponseEntity.ok(questService.getDailyQuests(profile));
    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<String> claimQuestReward(@PathVariable UUID id) {
        UserProfile profile = userProfileService.getCurrentProfile();

        try{
            questService.claimReward(id, profile);
            return ResponseEntity.ok("Claimed!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
