package com.snakeGame.snakeGame.service;

import com.snakeGame.snakeGame.dto.ModeWiseTopScore;
import com.snakeGame.snakeGame.model.Score;
import com.snakeGame.snakeGame.model.User;
import com.snakeGame.snakeGame.respository.ScoreRepository;
import com.snakeGame.snakeGame.respository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void saveScore(Long userId, int score, String mode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Score newScore = new Score();
        newScore.setUser(user);
        newScore.setScore(score);
        newScore.setMode(mode);
        scoreRepository.save(newScore);
    }

    public List<Score> getTopScores() {
        List<Score> scores = new ArrayList<>();
        scores.addAll(scoreRepository.findFirst3ByModeOrderByScoreDesc("easy"));
        scores.addAll(scoreRepository.findFirst3ByModeOrderByScoreDesc("medium"));
        scores.addAll(scoreRepository.findFirst3ByModeOrderByScoreDesc("hard"));
        return scores;
    }

    @Override
    public ModeWiseTopScore getUserModeWiseTopScore(Long userId) {
        List<Object[]> results = scoreRepository.findTopScoresByMode(userId);
        ModeWiseTopScore modeWiseTopScore = new ModeWiseTopScore();

        Map<String, Integer> modeToScoreMap = new HashMap<>();
        for (Object[] result : results) {
            String mode = (String) result[0];
            Integer topScore = (Integer) result[1];
            modeToScoreMap.put(mode, topScore);
        }

        modeWiseTopScore.setEasy(modeToScoreMap.getOrDefault("easy", 0));
        modeWiseTopScore.setMedium(modeToScoreMap.getOrDefault("medium", 0));
        modeWiseTopScore.setHard(modeToScoreMap.getOrDefault("hard", 0));

        return modeWiseTopScore;
    }
}
