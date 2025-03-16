package com.snakeGame.snakeGame.controller;

import com.snakeGame.snakeGame.model.Score;
import com.snakeGame.snakeGame.model.User;
import com.snakeGame.snakeGame.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Allow cross-origin requests from Angular
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/login")
    public User loginUser(@RequestParam String name) {
        return gameService.loginUser(name);
    }

    @PostMapping("/score")
    public void saveScore(@RequestParam Long userId, @RequestParam int score) {
        gameService.saveScore(userId, score);
    }

    @GetMapping("/scores")
    public List<Score> getTopScores() {
        return gameService.getTopScores();
    }
}
