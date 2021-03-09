package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EloRatingServiceImpl implements EloRatingService {

    private static final double ADJUSTMENT_FACTOR = 16.0;

    private final PlayerManager playerManager;

    @Override
    public void updatePlayerRankings(Player user, Player opponent, int userWin, int opponentWin) {
        double userExpected = logisticProbability(user.getScore(), opponent.getScore());
        double opponentExpected = logisticProbability(opponent.getScore(), user.getScore());
        user.setScore(calculateScore(user.getScore(), winRate(userWin, opponentWin), userExpected));
        playerManager.updatePlayer(user);
        opponent.setScore(calculateScore(opponent.getScore(), winRate(opponentWin, userWin), opponentExpected));
        playerManager.updatePlayer(opponent);
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
