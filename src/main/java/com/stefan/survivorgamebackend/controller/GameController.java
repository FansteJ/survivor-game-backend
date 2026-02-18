package com.stefan.survivorgamebackend.controller;

import com.stefan.survivorgamebackend.dto.FinishGameSessionRequest;
import com.stefan.survivorgamebackend.service.FinishGameSessionService;
import com.stefan.survivorgamebackend.service.StartGameSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/game")
@AllArgsConstructor
public class GameController {
    private final StartGameSessionService startGameSessionService;
    private final FinishGameSessionService finishGameSessionService;

    @PostMapping("/start")
    public ResponseEntity<UUID> startGame() {
        return ResponseEntity.ok(startGameSessionService.startGameSession());
    }

    @PostMapping("/finish")
    public ResponseEntity<?> finishGame(@RequestBody FinishGameSessionRequest finishGameSessionRequest) {
        finishGameSessionService.finishGameSession(finishGameSessionRequest);
        return ResponseEntity.ok().build();
    }
}
