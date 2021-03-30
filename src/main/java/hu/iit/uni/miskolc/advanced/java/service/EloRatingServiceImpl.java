package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EloRatingServiceImpl implements EloRatingService {

    private final PlayerManager playerManager;

    private final EloRatingCalculator eloRatingCalculator;

    @Override
    public void updatePlayerRankings(Player user, Player opponent, int userWin, int opponentWin) {
        EloRatingCalculator.CalculatedScores calculatedScores = eloRatingCalculator.calculateScores(
                new EloRatingCalculator.GameResult(
                        user.getScore(),
                        opponent.getScore(),
                        userWin,
                        opponentWin
                ));
        user.setScore(calculatedScores.getUser1());
        playerManager.updatePlayer(user);
        opponent.setScore(calculatedScores.getUser2());
        playerManager.updatePlayer(opponent);
    }

}
