package hu.iit.uni.miskolc.advanced.java.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public interface EloRatingCalculator {

    CalculatedScores calculateScores(GameResult gameResult);

    @AllArgsConstructor
    @Data
    @Builder
    class CalculatedScores {

        private int user1;

        private int user2;
    }

    @AllArgsConstructor
    @Data
    @Builder
    class GameResult {
        private int user1score;

        private int user2score;

        private int user1win;

        private int user2win;
    }
}
