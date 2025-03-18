package com.snakeGame.snakeGame.service;

import com.snakeGame.snakeGame.model.Score;
import com.snakeGame.snakeGame.model.User;

import java.util.List;

public interface GameService {
    User loginUser(String name);

    void saveScore(Long userId, int score, String mode);

    List<Score> getTopScores();
}
