package com.snakeGame.snakeGame.controller;

import com.snakeGame.snakeGame.dto.AuthResponse;
import com.snakeGame.snakeGame.dto.ModeWiseTopScore;
import com.snakeGame.snakeGame.model.Score;
import com.snakeGame.snakeGame.model.User;
import com.snakeGame.snakeGame.service.GameService;
import com.snakeGame.snakeGame.utility.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService gameService;
    private final JWTUtil jwtUtil;

    public GameController(GameService gameService, JWTUtil jwtUtil) {
        this.gameService = gameService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Logs in a user and returns a JWT token along with user details")
    @PostMapping("/login")
    public AuthResponse loginUser(@RequestParam String name) {
        User user = gameService.loginUser(name);
        String token = jwtUtil.generateToken(user.getUserName());
        return new AuthResponse(token, user.getId(), user.getUserName());
    }

    @Operation(summary = "Saves the user's score along with the game mode", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("/score")
    public void saveScore(@RequestParam Long userId, @RequestParam int score, @RequestParam String mode) {
        gameService.saveScore(userId, score, mode);
    }

    @Operation(summary = "Retrieves the top 3 scores of each mode", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/scores")
    public List<Score> getTopScores() {
        return gameService.getTopScores();
    }

    @Operation(summary = "Saves the user's score along with the game mode", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("/modeWiseTopScore")
    public ModeWiseTopScore getUserModeWiseTopScore(@RequestParam Long userId) {
        return gameService.getUserModeWiseTopScore(userId);
    }
}
