package com.snakeGame.snakeGame.service;

import com.snakeGame.snakeGame.model.Score;
import com.snakeGame.snakeGame.model.User;
import com.snakeGame.snakeGame.respository.ScoreRepository;
import com.snakeGame.snakeGame.respository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class GameServiceImpl implements GameService{
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    public User loginUser(String userName) {
        return userRepository.findByUserName(userName).orElseGet(() -> {
            User newUser = new User();
            newUser.setUserName(userName);
            return userRepository.save(newUser);
        });
    }

    public void saveScore(Long userId, int score) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Score newScore = new Score();
        newScore.setUser(user);
        newScore.setScore(score);
        scoreRepository.save(newScore);
    }

    public List<Score> getTopScores() {
        return scoreRepository.findTop10ByOrderByScoreDesc();
    }
}
