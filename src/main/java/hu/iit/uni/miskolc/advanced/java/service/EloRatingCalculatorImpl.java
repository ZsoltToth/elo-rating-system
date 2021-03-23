package hu.iit.uni.miskolc.advanced.java.service;

public class EloRatingCalculatorImpl implements EloRatingCalculator {

    private static final double ADJUSTMENT_FACTOR = 16.0;

    @Override
    public CalculatedScores calculateScores(GameResult gameResult) {
        double user1Expected = logisticProbability(gameResult.getUser1score(), gameResult.getUser2score());
        int user1updatedScore = calculateScore(
                gameResult.getUser1score(),
                winRate(gameResult.getUser1win(), gameResult.getUser2win()),
                user1Expected);
        double opponentExpected = logisticProbability(gameResult.getUser2score(), gameResult.getUser1score());
        int user2updateScore = calculateScore(
                gameResult.getUser2score(),
                winRate(gameResult.getUser2win(), gameResult.getUser1win()),
                opponentExpected);
        return new CalculatedScores(user1updatedScore, user2updateScore);
    }

    private double logisticProbability(double userRating, double opponentRating) {
        final double exponentialConstant = 400.0;
        return 1.0 / (1.0 + Math.pow(10.0, (opponentRating - userRating) / exponentialConstant));
    }

    private double winRate(int userWin, int opponentWin) {
        return (double) userWin / (double) (userWin + opponentWin);
    }

    private int calculateScore(int oldScore, double actualWinRate, double expectedWin) {
        double adjustment = ADJUSTMENT_FACTOR * (actualWinRate - expectedWin);
        return (int) (oldScore + Math.round(adjustment));
    }
}
