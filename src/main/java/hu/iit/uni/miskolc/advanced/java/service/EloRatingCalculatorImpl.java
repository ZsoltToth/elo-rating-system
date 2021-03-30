package hu.iit.uni.miskolc.advanced.java.service;

import org.springframework.stereotype.Service;

@Service
public class EloRatingCalculatorImpl implements EloRatingCalculator {

    private static final double ADJUSTMENT_FACTOR = 16.0;

    private static final double CERTAIN_EVENT_PROBABILITY = 1.0;

    private static final double IMPOSIBLE_EVENT_PROBABILITY = 0.0;

    @Override
    public CalculatedScores calculateScores(GameResult gameResult) {
        double winnerChance = logisticProbability(
                gameResult.getWinnerOriginalScore(),
                gameResult.getLoserOriginalScore());
        int winnerUpdatedScore = calculateScore(
                gameResult.getWinnerOriginalScore(),
                true,
                winnerChance);
        int loserUpdatedScore = calculateScore(
                gameResult.getLoserOriginalScore(),
                false,
                (CERTAIN_EVENT_PROBABILITY - winnerChance));
        return new CalculatedScores(winnerUpdatedScore, loserUpdatedScore);
    }

    private double logisticProbability(double winnerScore, double loserScore) {
        final double exponentialConstant = 400.0;
        return 1.0 / (1.0 + Math.pow(10.0, (loserScore - winnerScore) / exponentialConstant));
    }

    private int calculateScore(int oldScore, boolean hasWon, double expectedWin) {
        final double actualWinRate = hasWon ? CERTAIN_EVENT_PROBABILITY : IMPOSIBLE_EVENT_PROBABILITY;
        double adjustment = ADJUSTMENT_FACTOR * (actualWinRate - expectedWin);
        return (int) (oldScore + Math.round(adjustment));
    }
}
