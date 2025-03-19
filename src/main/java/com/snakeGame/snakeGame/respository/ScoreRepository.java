package com.snakeGame.snakeGame.respository;

import com.snakeGame.snakeGame.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findFirst3ByModeOrderByScoreDesc(String mode);

    @Query("SELECT s.mode, MAX(s.score) FROM Score s WHERE s.user.id=:userId GROUP BY s.mode")
    List<Object[]> findTopScoresByMode(@Param("userId") Long userId);
}
