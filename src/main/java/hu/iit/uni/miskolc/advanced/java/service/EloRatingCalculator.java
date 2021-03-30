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

        private int winnerUpdatedScore;

        private int loserUpdatedScore;
    }

    @AllArgsConstructor
    @Data
    @Builder
    class GameResult {
        private int winnerOriginalScore;

        private int loserOriginalScore;

    }
}
